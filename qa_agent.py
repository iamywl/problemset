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

if sys.platform == "win32":
    if hasattr(sys.stdout, "reconfigure"):
        sys.stdout.reconfigure(encoding="utf-8", errors="replace")
    if hasattr(sys.stderr, "reconfigure"):
        sys.stderr.reconfigure(encoding="utf-8", errors="replace")

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

def resolve_problem_path(prob_dir_name):
    # Direct path
    p = os.path.join(BASE_DIR, prob_dir_name)
    if os.path.isdir(p):
        return p
    # Check within topic directories
    for entry in os.listdir(BASE_DIR):
        tp = os.path.join(BASE_DIR, entry)
        if os.path.isdir(tp) and not entry.startswith("."):
            sub_p = os.path.join(tp, prob_dir_name)
            if os.path.isdir(sub_p):
                return sub_p
    return None

def get_all_problem_dirs():
    dirs = []
    for entry in os.listdir(BASE_DIR):
        p = os.path.join(BASE_DIR, entry)
        if not os.path.isdir(p) or entry.startswith(".") or entry.startswith("__"):
            continue
        # Case 1: entry itself is a problem directory (legacy flat structure)
        if "_" in entry and entry.split("_")[0] in ["D1", "D2", "D3", "D4", "D5"]:
            dirs.append(entry)
        else:
            # Case 2: entry is a topic directory, search for problems under it
            for sub in os.listdir(p):
                sub_p = os.path.join(p, sub)
                if os.path.isdir(sub_p) and "_" in sub and sub.split("_")[0] in ["D1", "D2", "D3", "D4", "D5"]:
                    dirs.append(os.path.join(entry, sub).replace("\\", "/"))
    return sorted(dirs)

# ---------------------------------------------------------------------------
# [기능 1] 주제별 / 난이도별 분포 감사 및 편향 진단 (Audit)
# ---------------------------------------------------------------------------
def audit_distribution():
    dirs = get_all_problem_dirs()
    topic_data = defaultdict(lambda: {"D2": [], "D3": [], "D4": [], "D5": [], "other": []})

    for d in dirs:
        parts = os.path.basename(d).split("_")
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
    print(f"{'주제 (Topic)':<18} | {'D2':>4} | {'D3':>4} | {'D4':>4} | {'D5':>4} | {'합계':>5} | {'D2개념':>7} | {'상태(>=5)':>9}")
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

        # D2, D3, D4 각각 5문제 이상 충족 여부
        is_balanced = (c_d2 >= 5 and c_d3 >= 5 and c_d4 >= 5 and has_concept)
        status_str = "✅ 균형" if is_balanced else "⚠️ 부족"

        if not is_balanced:
            deficient_topics.append({
                "topic": topic,
                "D2": c_d2,
                "D3": c_d3,
                "D4": c_d4,
                "need_d2": max(0, 5 - c_d2),
                "need_d3": max(0, 5 - c_d3),
                "need_d4": max(0, 5 - c_d4),
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

    print("\n💡 [난이도 계단식 조절(Ladder Progression) 및 증설 가이드]")
    print(" - 난이도 조절이 급격해지는 것을 방지하기 위해 문항 수 증설을 공식 허용합니다.")
    print(" - D2 내부: [개념 뼈대(~에_대해서)] -> [단순 기능 구현] -> [기초 조건 응용(징검다리)] 순으로 구성.")
    print(" - 세부 주제별 계단식 구조 점검: 'python qa_agent.py ladder [주제]' 명령어 활용.")

    return deficient_topics

# ---------------------------------------------------------------------------
# [기능 1-2] 난이도 계단식 편차 진단 (Ladder Audit)
# ---------------------------------------------------------------------------
def audit_ladder(target_topic=None):
    dirs = get_all_problem_dirs()
    topic_data = defaultdict(lambda: {"D2": [], "D3": [], "D4": [], "D5": [], "other": []})

    for d in dirs:
        parts = os.path.basename(d).split("_")
        diff = parts[0]
        topic = parts[1] if len(parts) > 1 else "기타"
        if diff in topic_data[topic]:
            topic_data[topic][diff].append(d)

    print("\n" + "=" * 80)
    print(" 🪜 [QA Agent] 주제별 계단식 난이도(Ladder Progression) 및 편차 진단")
    print("=" * 80)

    topics_to_check = [target_topic] if target_topic and target_topic in topic_data else sorted(topic_data.keys())

    for topic in topics_to_check:
        diffs = topic_data[topic]
        c_d2 = len(diffs["D2"])
        c_d3 = len(diffs["D3"])
        c_d4 = len(diffs["D4"])
        c_d5 = len(diffs["D5"])
        tot = c_d2 + c_d3 + c_d4 + c_d5

        # D2 세부 인지 사다리(Micro-Ladder) 분류
        concept_probs = [d for d in diffs["D2"] if "에_대해서" in d or "에대해서" in d]
        step1_probs = [d for d in diffs["D2"] if any(k in d for k in ["UP_DOWN", "단순_부모", "1차원_배열_반으로", "원리", "직관", "기본기"])]
        step2_probs = [d for d in diffs["D2"] if any(k in d for k in ["단계수", "2등분_원소_합", "루트_노드_직접", "기본", "카운팅", "탐색기"]) and d not in step1_probs and d not in concept_probs]
        buffer_probs = [d for d in diffs["D2"] if d not in concept_probs and d not in step1_probs and d not in step2_probs]

        print(f"\n📂 [{topic}] (총 {tot}문제: D2:{c_d2}, D3:{c_d3}, D4:{c_d4}, D5:{c_d5})")
        print("  1. D2 기초 레벨 (5단계 미세 사다리 & 징검다리):")
        if step1_probs:
            for p in step1_probs:
                print(f"     [Lv.1 직관/1회판정] {p} (루프 없이 1회 판정/직접 조회)")
        if step2_probs:
            for p in step2_probs:
                print(f"     [Lv.2 단일루프/추적] {p} (기초 제어 및 단계 추적)")
        if concept_probs:
            for p in concept_probs:
                print(f"     [Lv.3 개념 뼈대 완결] {p} (동작원리/Trace/기본구현)")
        else:
            print("     ⚠️ [Lv.3 개념 뼈대] '...에_대해서' 개념 문제 미보유!")
        if buffer_probs:
            for p in buffer_probs:
                print(f"     [Lv.4 경계완충/징검] {p} (D3 도약 전 경계/다중조건 완충)")

        print("  2. D3 실전 응용 레벨 (20인 실생활 스토리텔링):")
        for p in diffs["D3"][:3]:
            print(f"     [Lv.5 실전 응용] {p}")
        if len(diffs["D3"]) > 3:
            print(f"     ... 외 {len(diffs['D3']) - 3}문제")

        print("  3. D4 심화 레벨 (복합 제약 및 최적화):")
        for p in diffs["D4"][:3]:
            print(f"     [Lv.6 심화 최적화] {p}")
        if len(diffs["D4"]) > 3:
            print(f"     ... 외 {len(diffs['D4']) - 3}문제")

        if diffs["D5"]:
            print("  4. D5 B형 Pro 레벨 (No-STL/Zero-GC/메모리풀):")
            for p in diffs["D5"]:
                print(f"     [Lv.7 Pro 설계]   {p}")

        # 편차 진단 코멘트
        if c_d2 >= 7:
            print("  🎯 [편차 진단]: D2 내부 징검다리(7문제 이상) 완비로 난이도 급상승 완전 해소!")
        elif c_d2 >= 5 and c_d3 >= 5 and c_d4 >= 5 and concept_probs:
            print("  ✅ [편차 진단]: D2 뼈대부터 D4 심화까지 완만한 계단식 난이도 구축 완료.")
        else:
            print("  ⚠️ [편차 진단]: 계단 연결용 징검다리 문제 보충 권장.")

    print("\n" + "=" * 80)


# ---------------------------------------------------------------------------
# [기능 2] 문제 내용 품질 검사 (Inspect Content)
# ---------------------------------------------------------------------------
def inspect_problem_content(prob_dir_name):
    prob_dir = resolve_problem_path(prob_dir_name)
    if not prob_dir or not os.path.isdir(prob_dir):
        return False, "문제 디렉토리 없음"
    desc_path = os.path.join(prob_dir, "문제.txt")
    if not os.path.exists(desc_path):
        return False, "문제.txt 파일 없음"

    with open(desc_path, "r", encoding="utf-8") as f:
        content = f.read()

    parts = os.path.basename(prob_dir_name).split("_")
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
    prob_dir = resolve_problem_path(prob_dir_name)
    if not prob_dir or not os.path.isdir(prob_dir):
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
        text=True,
        encoding="utf-8",
        errors="replace"
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
        text=True,
        encoding="utf-8",
        errors="replace"
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
        text=True,
        encoding="utf-8",
        errors="replace"
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
# [기능 5] 문항별 업로드 상태 추적 및 엑셀 실시간 동기화 (Upload Management)
# ---------------------------------------------------------------------------
def audit_upload():
    import openpyxl
    xlsx_path = os.path.join(BASE_DIR, "SWEA_알고리즘_문제집_커리큘럼.xlsx")
    if not os.path.exists(xlsx_path):
        print(f"[ERROR] 엑셀 파일이 존재하지 않습니다: {xlsx_path}")
        return

    wb = openpyxl.load_workbook(xlsx_path, data_only=True)
    if "📋 전체_문제_목록" not in wb.sheetnames:
        print("[ERROR] '📋 전체_문제_목록' 시트가 없습니다.")
        return

    ws = wb["📋 전체_문제_목록"]
    total = 0
    done = 0
    prog = 0
    pending = 0
    topic_stats = defaultdict(lambda: {"total": 0, "done": 0, "prog": 0, "pending": 0})

    for row in range(2, ws.max_row + 1):
        no = ws.cell(row, 1).value
        if no is None:
            continue
        total += 1
        topic = str(ws.cell(row, 2).value or '')
        status = str(ws.cell(row, 5).value or '⬜ 대기')

        topic_stats[topic]["total"] += 1
        if "완료" in status:
            done += 1
            topic_stats[topic]["done"] += 1
        elif "진행" in status or "검토" in status:
            prog += 1
            topic_stats[topic]["prog"] += 1
        else:
            pending += 1
            topic_stats[topic]["pending"] += 1

    rate = (done / total * 100) if total > 0 else 0.0

    print("\n" + "=" * 80)
    print(" 🚀 [QA Agent] 전체 358문항 업로드 현황 실시간 리포트")
    print("=" * 80)
    print(f" • 총 관리 대상 문항: {total}개")
    print(f" • ✅ 업로드 완료:   {done}개 ({rate:.1f}%)")
    print(f" • ⏳ 검토 및 진행중: {prog}개 ({(prog/total*100 if total else 0):.1f}%)")
    print(f" • ⬜ 업로드 대기:   {pending}개 ({(pending/total*100 if total else 0):.1f}%)")
    print("-" * 80)
    print(f"{'주제 (Topic)':<18} | {'전체':>5} | {'완료':>5} | {'진행중':>6} | {'대기':>5} | {'달성률':>7}")
    print("-" * 80)
    for topic, st in sorted(topic_stats.items()):
        t_tot = st["total"]
        t_done = st["done"]
        t_prog = st["prog"]
        t_pend = st["pending"]
        t_rate = (t_done / t_tot * 100) if t_tot else 0.0
        print(f"{topic:<18} | {t_tot:5d} | {t_done:5d} | {t_prog:6d} | {t_pend:5d} | {t_rate:6.1f}%")
    print("=" * 80)
    print("💡 문항 상태 변경: python qa_agent.py upload --mark [문제경로/제목] [완료/진행/대기] [--id SWEA번호]")

def mark_upload_status(target, status_input, swea_id=None, date_str=None, memo=None):
    import openpyxl
    import datetime

    status_map = {
        "완료": "✅ 업로드 완료",
        "done": "✅ 업로드 완료",
        "1": "✅ 업로드 완료",
        "진행": "⏳ 검토중",
        "진행중": "⏳ 검토중",
        "검토": "⏳ 검토중",
        "검토중": "⏳ 검토중",
        "prog": "⏳ 검토중",
        "2": "⏳ 검토중",
        "대기": "⬜ 대기",
        "미업로드": "⬜ 대기",
        "0": "⬜ 대기"
    }
    status = status_map.get(status_input.lower(), status_input)
    today = date_str or datetime.date.today().strftime("%Y-%m-%d")
    target_clean = os.path.basename(target)

    xlsx1 = os.path.join(BASE_DIR, "SWEA_알고리즘_문제집_커리큘럼.xlsx")
    updated_1 = False
    if os.path.exists(xlsx1):
        wb1 = openpyxl.load_workbook(xlsx1)
        if "📋 전체_문제_목록" in wb1.sheetnames:
            ws = wb1["📋 전체_문제_목록"]
            for r in range(2, ws.max_row + 1):
                p_title = str(ws.cell(r, 4).value or '')
                p_path = str(ws.cell(r, 11).value or '')
                if target_clean in p_title or target_clean in p_path:
                    ws.cell(r, 5, value=status)
                    ws.cell(r, 6, value=today if "완료" in status else "-")
                    if swea_id:
                        ws.cell(r, 7, value=str(swea_id))
                    if memo:
                        ws.cell(r, 14, value=str(memo))
                    updated_1 = True
                    print(f" -> [커리큘럼 엑셀] '{p_title}' -> {status} 갱신 완료")
        wb1.save(xlsx1)

    xlsx2 = os.path.join(BASE_DIR, "year", "yearlySchedule.v2.10.xlsx")
    updated_2 = False
    if os.path.exists(xlsx2):
        wb2 = openpyxl.load_workbook(xlsx2)
        if "06_SWEA_생성문제_358_업로드_DB" in wb2.sheetnames:
            ws = wb2["06_SWEA_생성문제_358_업로드_DB"]
            for r in range(2, ws.max_row + 1):
                p_title = str(ws.cell(r, 4).value or '')
                p_path = str(ws.cell(r, 9).value or '')
                if target_clean in p_title or target_clean in p_path:
                    ws.cell(r, 5, value=status)
                    ws.cell(r, 6, value=today if "완료" in status else "-")
                    if swea_id:
                        ws.cell(r, 7, value=str(swea_id))
                    updated_2 = True
                    print(f" -> [연간일정 엑셀] '{p_title}' -> {status} 갱신 완료")
        wb2.save(xlsx2)

    if updated_1 or updated_2:
        print(f"🎉 '{target}'의 업로드 상태가 양쪽 엑셀 통합 문서에 실시간 반영되었습니다.")
    else:
        print(f"[WARNING] 대상 문제 '{target}'을(를) 엑셀에서 찾지 못했습니다.")

# ---------------------------------------------------------------------------
# 메인 CLI 엔트리포인트
# ---------------------------------------------------------------------------
def main():
    parser = argparse.ArgumentParser(description="SWEA Problem Set QA Agent")
    subparsers = parser.add_subparsers(dest="command")

    subparsers.add_parser("audit", help="주제별/난이도별 분포 감사 및 편향/결핍 진단")
    
    ladder_p = subparsers.add_parser("ladder", help="주제별 계단식 난이도 편차 및 징검다리 구조 진단")
    ladder_p.add_argument("topic", nargs="?", default=None, help="진단할 특정 주제명 (생략 시 전체)")

    test_p = subparsers.add_parser("verify", help="문제 채점 및 검증")
    test_p.add_argument("target", nargs="?", default="--all", help="검증 대상 문제 디렉토리명 또는 --all")
    test_p.add_argument("-v", "--verbose", action="store_true", help="상세 출력")

    inspect_p = subparsers.add_parser("inspect", help="문제 텍스트 품질 검사")
    inspect_p.add_argument("target", help="검사 대상 문제 디렉토리명")

    upload_p = subparsers.add_parser("upload", help="358문항 업로드 현황 조회 및 마킹")
    upload_p.add_argument("--mark", nargs=2, metavar=("TARGET", "STATUS"), help="특정 문제의 업로드 상태 변경 (예: --mark 슬라이딩윈도우_뒤집기 완료)")
    upload_p.add_argument("--id", default=None, help="SWEA 문제번호 또는 URL")
    upload_p.add_argument("--date", default=None, help="업로드 일자 (YYYY-MM-DD)")
    upload_p.add_argument("--memo", default=None, help="업로드 메모")

    args = parser.parse_args()

    if args.command == "audit":
        audit_distribution()
    elif args.command == "ladder":
        audit_ladder(args.topic)
    elif args.command == "verify":
        if args.target == "--all":
            verify_all()
        else:
            ok, msg = verify_problem(args.target, verbose=True)
            print(f"결과: {'✅ PASS' if ok else '❌ FAIL'} - {msg}")
    elif args.command == "inspect":
        ok, msg = inspect_problem_content(args.target)
        print(f"[{args.target}] 내용 품질: {'✅ 통과' if ok else '⚠️ 보완 필요'} ({msg})")
    elif args.command == "upload":
        if args.mark:
            target, status = args.mark
            mark_upload_status(target, status, args.id, args.date, args.memo)
        else:
            audit_upload()
    else:
        parser.print_help()

if __name__ == "__main__":
    main()
