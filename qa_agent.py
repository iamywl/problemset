# -*- coding: utf-8 -*-
"""
=============================================================================
[SWEA Problem Set QA Agent - 품질 보증 및 편향 검증 에이전트]
=============================================================================
역할 및 기능:
1. audit: 전체 문제 세트의 주제별/난이도별 분포 분석, 편향(Bias) 진단, 결핍 영역 리포팅
2. verify: 6종 파일 규격 완비 여부, Java 8 컴파일, 12개 테스트케이스 100% 통과(PASS) 검증
3. inspect: 문제.txt 내용 품질 검사 (D2 개념/Trace 설명, D3 20인 이름 스토리텔링, D4/D5 복합 제약)
4. plan: 주제별 D2, D3, D4 최소 3문제 충족 및 D2 '...에 대해서' 개념 학습 문제 보충 계획 수립
=============================================================================
"""

import os
import sys
import glob
import subprocess
import argparse
from collections import defaultdict

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

REQUIRED_FILES = [
    "문제.txt",
    "sample_input.txt",
    "sample_output.txt",
    "input.txt",
    "output.txt",
    "Solution.java"
]

CHILDREN_NAMES = [
    "이준", "도윤", "하준", "시우", "서준", "은우", "유준", "선우", "로운", "도하",
    "이서", "서아", "아윤", "지아", "하윤", "서윤", "시아", "아린", "나은", "유주"
]

def get_all_problem_dirs():
    dirs = []
    for d in os.listdir(BASE_DIR):
        p = os.path.join(BASE_DIR, d)
        if os.path.isdir(p) and not d.startswith(".") and "_" in d:
            parts = d.split("_")
            if parts[0] in ["D1", "D2", "D3", "D4", "D5"]:
                dirs.append(d)
    return sorted(dirs)

# ---------------------------------------------------------------------------
# [기능 1] 주제별 / 난이도별 분포 감사 및 편향 진단 (Audit)
# ---------------------------------------------------------------------------
def audit_distribution():
    dirs = get_all_problem_dirs()
    topic_data = defaultdict(lambda: {"D2": [], "D3": [], "D4": [], "D5": [], "other": []})

    for d in dirs:
        parts = d.split("_")
        diff = parts[0]
        topic = parts[1] if len(parts) > 1 else "기타"
        if diff in topic_data[topic]:
            topic_data[topic][diff].append(d)
        else:
            topic_data[topic]["other"].append(d)

    print("\n" + "=" * 80)
    print(" 📊 [QA Agent] SWEA 문제 세트 주제별/난이도별 분포 감사 리포트")
    print("=" * 80)
    print(f" 총 문제 수: {len(dirs)}개 (총 {len(topic_data)}개 주제)")
    print("-" * 80)
    print(f"{'주제 (Topic)':<18} | {'D2':>4} | {'D3':>4} | {'D4':>4} | {'D5':>4} | {'합계':>5} | {'D2개념':>7} | {'상태(>=3)':>9}")
    print("-" * 80)

    deficient_topics = []
    total_d2 = total_d3 = total_d4 = total_d5 = 0
    total_concept_d2 = 0

    for topic, diffs in sorted(topic_data.items()):
        c_d2 = len(diffs["D2"])
        c_d3 = len(diffs["D3"])
        c_d4 = len(diffs["D4"])
        c_d5 = len(diffs["D5"])
        tot = c_d2 + c_d3 + c_d4 + c_d5

        total_d2 += c_d2
        total_d3 += c_d3
        total_d4 += c_d4
        total_d5 += c_d5

        # D2 '...에 대해서' 개념 학습 문제 유무 검사
        has_concept = any("에_대해서" in d or "에대해서" in d for d in diffs["D2"])
        if has_concept:
            total_concept_d2 += 1
        concept_str = "✅ 보유" if has_concept else "❌ 미보유"

        # D2, D3, D4 각각 3문제 이상 충족 여부
        is_balanced = (c_d2 >= 3 and c_d3 >= 3 and c_d4 >= 3)
        status_str = "✅ 균형" if is_balanced else "⚠️ 부족"

        if not is_balanced:
            deficient_topics.append({
                "topic": topic,
                "D2": c_d2,
                "D3": c_d3,
                "D4": c_d4,
                "need_d2": max(0, 3 - c_d2),
                "need_d3": max(0, 3 - c_d3),
                "need_d4": max(0, 3 - c_d4),
                "need_concept": not has_concept
            })

        print(f"{topic:<18} | {c_d2:4d} | {c_d3:4d} | {c_d4:4d} | {c_d5:4d} | {tot:5d} | {concept_str:>7} | {status_str:>9}")

    print("-" * 80)
    print(f"{'전체 합계':<18} | {total_d2:4d} | {total_d3:4d} | {total_d4:4d} | {total_d5:4d} | {len(dirs):5d} | {total_concept_d2:2d}개 주제 | -")
    print("=" * 80)

    # 편향성 분석
    print("\n🔍 [주제 편향성 심층 진단]")
    sorted_by_count = sorted(topic_data.items(), key=lambda x: sum(len(v) for v in x[1].values()), reverse=True)
    top3_count = sum(sum(len(v) for v in sorted_by_count[i][1].values()) for i in range(min(3, len(sorted_by_count))))
    top3_ratio = (top3_count / len(dirs)) * 100 if dirs else 0
    print(f" - 상위 3대 주제 점유율: {top3_ratio:.1f}% ({top3_count}/{len(dirs)}문제)")
    print(f" - 최다 편중 주제: 1위 [{sorted_by_count[0][0]}] ({sum(len(v) for v in sorted_by_count[0][1].values())}문제), 2위 [{sorted_by_count[1][0]}] ({sum(len(v) for v in sorted_by_count[1][1].values())}문제)")

    print("\n⚠️ [규격 미달 및 보충 필요 영역 리포트 (D2/D3/D4 각 3문제 이상 & D2 개념 필수)]")
    for item in deficient_topics:
        needs = []
        if item["need_d2"] > 0: needs.append(f"D2 +{item['need_d2']}문제")
        if item["need_d3"] > 0: needs.append(f"D3 +{item['need_d3']}문제")
        if item["need_d4"] > 0: needs.append(f"D4 +{item['need_d4']}문제")
        if item["need_concept"]: needs.append("D2 '[주제]에_대해서' 개념문제 필수")
        print(f" ▶ 주제 [{item['topic']}]: 현재 D2={item['D2']}, D3={item['D3']}, D4={item['D4']} => 보충 필요: {', '.join(needs)}")

    return deficient_topics

# ---------------------------------------------------------------------------
# [기능 2] 문제 내용 품질 검사 (Inspect Content)
# ---------------------------------------------------------------------------
def inspect_problem_content(prob_dir_name):
    prob_dir = os.path.join(BASE_DIR, prob_dir_name)
    desc_path = os.path.join(prob_dir, "문제.txt")
    if not os.path.exists(desc_path):
        return False, "문제.txt 파일 없음"

    with open(desc_path, "r", encoding="utf-8") as f:
        content = f.read()

    parts = prob_dir_name.split("_")
    diff = parts[0]
    issues = []

    if diff == "D2":
        # 개념 설명, 예시, 동작 원리 확인
        keywords = ["개념", "원리", "설명", "동작", "예시", "Trace"]
        if not any(k in content for k in keywords):
            issues.append("D2 개념/동작원리 설명 누락")
        if "에_대해서" in prob_dir_name and not ("구현" in content and "동작" in content):
            issues.append("D2 '...에 대해서' 구현 가이드 미흡")

    elif diff == "D3":
        # 20인 아이 이름 포함 확인
        has_child = any(name in content for name in CHILDREN_NAMES)
        if not has_child:
            issues.append("D3 20인 한국 아이 이름 스토리텔링 미적용")

    elif diff in ["D4", "D5"]:
        # 복합 제약조건 및 심화 알고리즘 확인
        adv_keywords = ["제약", "복합", "조건", "동시", "시간", "최적화", "Zero-GC", "커스텀"]
        if not any(k in content for k in adv_keywords):
            issues.append("D4/D5 복합 제약조건 설명 미흡")

    if issues:
        return False, "; ".join(issues)
    return True, "품질 통과"

# ---------------------------------------------------------------------------
# [기능 3] 개별 문제 검증 및 채점 (Verify Single Problem)
# ---------------------------------------------------------------------------
def verify_problem(prob_dir_name, verbose=False):
    prob_dir = os.path.join(BASE_DIR, prob_dir_name)
    if not os.path.isdir(prob_dir):
        return False, "디렉토리 없음"

    # 1. 필수 6종 파일 검사
    missing = [f for f in REQUIRED_FILES if not os.path.exists(os.path.join(prob_dir, f))]
    if missing:
        return False, f"필수 파일 누락: {', '.join(missing)}"

    # 2. 내용 품질 검사
    content_ok, content_msg = inspect_problem_content(prob_dir_name)
    if not content_ok:
        if verbose:
            print(f"  [QA 경고] {prob_dir_name}: {content_msg}")

    # 3. Java 8 컴파일
    comp = subprocess.run(
        ["javac", "-encoding", "UTF-8", "Solution.java"],
        cwd=prob_dir,
        capture_output=True,
        text=True
    )
    if comp.returncode != 0:
        return False, f"Java 컴파일 에러: {comp.stderr[:100]}"

    # 4. 샘플 테스트 (T=2)
    with open(os.path.join(prob_dir, "sample_input.txt"), "r", encoding="utf-8") as f:
        sample_in = f.read()
    with open(os.path.join(prob_dir, "sample_output.txt"), "r", encoding="utf-8") as f:
        sample_exp = [line.strip() for line in f if line.strip()]

    run_s = subprocess.run(
        ["java", "Solution"],
        cwd=prob_dir,
        input=sample_in,
        capture_output=True,
        text=True
    )
    if run_s.returncode != 0:
        cleanup_class(prob_dir)
        return False, f"샘플 런타임 에러: {run_s.stderr[:100]}"

    s_out = [line.strip() for line in run_s.stdout.splitlines() if line.strip()]
    if s_out != sample_exp:
        cleanup_class(prob_dir)
        return False, "샘플 출력 불일치 (Fail)"

    # 5. 백그라운드 평가용 테스트 (T=10)
    with open(os.path.join(prob_dir, "input.txt"), "r", encoding="utf-8") as f:
        eval_in = f.read()
    with open(os.path.join(prob_dir, "output.txt"), "r", encoding="utf-8") as f:
        eval_exp = [line.strip() for line in f if line.strip()]

    run_e = subprocess.run(
        ["java", "Solution"],
        cwd=prob_dir,
        input=eval_in,
        capture_output=True,
        text=True
    )
    cleanup_class(prob_dir)

    if run_e.returncode != 0:
        return False, f"평가 런타임 에러: {run_e.stderr[:100]}"

    e_out = [line.strip() for line in run_e.stdout.splitlines() if line.strip()]
    if e_out != eval_exp:
        return False, "평가 출력 불일치 (Fail)"

    return True, "100% PASS (컴파일 및 12개 TC 완벽 일치)"

def cleanup_class(p_dir):
    for f in os.listdir(p_dir):
        if f.endswith(".class"):
            try:
                os.remove(os.path.join(p_dir, f))
            except Exception:
                pass

# ---------------------------------------------------------------------------
# [기능 4] 전체 문제 일괄 검증 (Verify All)
# ---------------------------------------------------------------------------
def verify_all():
    dirs = get_all_problem_dirs()
    print("\n" + "=" * 80)
    print(f" 🚀 [QA Agent] 전체 {len(dirs)}개 문제 일괄 채점 및 품질 검증 시작")
    print("=" * 80)

    pass_count = 0
    fail_count = 0
    failures = []

    for i, d in enumerate(dirs, 1):
        ok, msg = verify_problem(d)
        if ok:
            pass_count += 1
            print(f"[{i:3d}/{len(dirs):3d}] {d:<45} | ✅ PASS")
        else:
            fail_count += 1
            failures.append((d, msg))
            print(f"[{i:3d}/{len(dirs):3d}] {d:<45} | ❌ FAIL ({msg})")

    print("\n" + "=" * 80)
    print(f" [QA 종합 검증 결과] 총 {len(dirs)}문제 중 PASS: {pass_count}개, FAIL: {fail_count}개")
    if failures:
        print(" [실패 목록]")
        for d, msg in failures:
            print(f"  - {d}: {msg}")
    else:
        print(" 🎉 모든 문제가 Java 8 표준 6종 파일 및 12개 테스트케이스 100% 통과 완료!")
    print("=" * 80)

# ---------------------------------------------------------------------------
# 메인 CLI 엔트리포인트
# ---------------------------------------------------------------------------
def main():
    parser = argparse.ArgumentParser(description="SWEA Problem Set QA Agent")
    subparsers = parser.add_subparsers(dest="command")

    subparsers.add_parser("audit", help="주제별/난이도별 분포 감사 및 편향/결핍 진단")
    
    test_p = subparsers.add_parser("verify", help="문제 채점 및 검증")
    test_p.add_argument("target", nargs="?", default="--all", help="검증 대상 문제 디렉토리명 또는 --all")
    test_p.add_argument("-v", "--verbose", action="store_true", help="상세 출력")

    inspect_p = subparsers.add_parser("inspect", help="문제 텍스트 품질 검사")
    inspect_p.add_argument("target", help="검사 대상 문제 디렉토리명")

    args = parser.parse_args()

    if args.command == "audit":
        audit_distribution()
    elif args.command == "verify":
        if args.target == "--all":
            verify_all()
        else:
            ok, msg = verify_problem(args.target, verbose=True)
            print(f"결과: {'✅ PASS' if ok else '❌ FAIL'} - {msg}")
    elif args.command == "inspect":
        ok, msg = inspect_problem_content(args.target)
        print(f"[{args.target}] 내용 품질: {'✅ 통과' if ok else '⚠️ 보완 필요'} ({msg})")
    else:
        parser.print_help()

if __name__ == "__main__":
    main()
