"""
=============================================================================
[USER REQUIREMENTS SPECIFICATION - 사용자 요구사항 명세]
=============================================================================
1. 아키텍처 및 디렉토리 관리 구조:
   - 최상위에 총괄 에이전트 파일(agent.py)이 단독으로 위치해야 한다.
   - 모든 문제 디렉토리들은 반드시 agent.py 하위에서 생성되고 관리되어야 한다.
   - 문제 생성 시마다 `[난이도]_[문제제목]` 형식으로 디렉토리를 생성해야 한다.
   - 각 문제 디렉토리 내부에는 오직 아래의 표준 6종 파일만 정갈하게 보관되어야 한다:
       1) 문제.txt (SWEA 표준 문제 설명, 제약사항, 입출력 포맷)
       2) sample_input.txt (샘플 입력 2개)
       3) sample_output.txt (샘플 정답 2개)
       4) input.txt (백그라운드 평가용 입력 10개)
       5) output.txt (백그라운드 평가용 정답 10개)
       6) Solution.java (SWEA 제출용 Java 8 표준 솔루션)
   - 각 문제 폴더 내부에 불필요한 스크립트나 컴파일 부산물(.class)을 남기지 않고 순수하게 유지한다.

2. 언어 및 제약조건 (핵심):
   - 제약조건은 "자바(Java)로만 가능"해야 한다. (Java 전용 코딩테스트)
   - Java 버전: Java 8 이상 지원
   - 클래스명: SWEA 규정에 따라 무조건 public class Solution 이어야 한다.
   - 시간 제한: 10개 테스트케이스 기준 2.0초 이내 (케이스당 0.2초 이내)
   - 메모리 제한: 힙(Heap) 메모리 256MB 이내

3. 문제 난이도 평가 및 주제별 균형 요건:
   - 주제별 난이도 균형 (핵심 요건):
       * 각 알고리즘 및 자료구조 주제별로 D2, D3, D4 난이도가 각각 최소 3문제 이상(주제당 최소 9문제) 균형 있게 구성되어야 한다.
       * 특정 주제로의 편향(Bias)을 방지하고 난이도 편차가 적도록 표준화된 복잡도 및 데이터 제약을 엄격히 준수한다.
   - D2 (기초 및 개념 완벽 학습형):
       * 단순 알고리즘 혹은 기본 자료구조를 사용하여 해결하는 문제.
       * D2 중 최소 1개 문제는 반드시 "그 개념을 완벽히 익히는 표준 예제"로 출제한다.
       * D2 대표 개념 문제의 제목 규격: `[주제]에 대해서` (예: `D2_스택_스택에_대해서`, `D2_큐_큐에_대해서`)
       * 문제 본문 규격:
         1) 해당 자료구조/알고리즘에 대한 상세하고 친절한 원리 설명 (예: "스택은 LIFO 구조로...")
         2) "구현하면 ~ 이렇게 된다"라는 구체적인 단계별 동작 Trace 예시
         3) "이것을 구현하라"는 명확한 구현 요구 명시
   - D3 (실전 응용 및 스토리텔링형):
       * 알고리즘 혹은 자료구조의 사용과 실전 응용력을 요구하는 문제.
       * 반드시 아래 지정된 한국 아이 대표 이름 20개 중 하나 이상을 활용한 실생활 시나리오 스토리텔링 기반으로 구체적 조건에 맞춰 구현한다:
         - 남자아이 이름 (10개): 이준, 도윤, 하준, 시우, 서준, 은우, 유준, 선우, 로운, 도하
         - 여자아이 이름 (10개): 이서, 서아, 아윤, 지아, 하윤, 서윤, 시아, 아린, 나은, 유주
   - D4 (심화 및 복합 제약조건형):
       * 2~3개 이상의 복합적인 조건을 유기적으로 결합하여 해결해야 하는 심화 문제.
       * 예: 모노톤 스택을 통한 O(N) 처리, 이진 탐색을 결합한 O(N log N) LIS, 세그먼트 트리를 통한 점 갱신/구간합 쿼리 등.
   - D5 (전문가 및 삼성 B형 Pro 대비형):
       * 삼성 코딩테스트 Pro(B형) 수준에 준하며, 다양한 고급 알고리즘과 커스텀 자료구조에 대한 깊은 이해도를 요구.
       * STL/표준 라이브러리 최소화/배제, Zero-GC 정적 메모리 풀링, 롤백(Undo) 지원, 비트마스킹 등 고성능 최적화 필수.

4. 병렬 에이전트(Parallel Agent / Worker Subagents) 운용 가이드:
   - 병렬 처리 전면 허용 및 적극 활용:
       * 대량의 문제 생성, 테스트케이스 검증, 자동 채점, 리팩토링 등의 대규모 작업 시 다중 병렬 에이전트(Worker Subagents)를 자유롭게 증설하여 동시에 분할 처리할 수 있다.
       * 세션별, 알고리즘 주제별, 난이도별로 작업을 독립 격리하여 병렬 에이전트에 위임함으로써 작업 효율과 처리 속도를 극대화한다.
   - 격리 작업 및 지속적 통합(Continuous Integration) 원칙:
       * 각 병렬 에이전트는 서로 다른 문제 디렉토리를 담당하여 파일 충돌을 방지한다.
       * 문제 단위 작업(6종 파일 생성 및 100% 컴파일/채점 통과)이 완료될 때마다 즉시 개별 `git commit` 및 `git push origin main`을 실행하여 원격 저장소 동기화 및 작업 손실 방지를 보장한다.

5. 테스트케이스 구성 규격:
   - 샘플 테스트케이스: 정확히 2개 (sample_input.txt, sample_output.txt)
   - 백그라운드 평가용 테스트케이스: 정확히 10개 (input.txt, output.txt)
   - 출력 형식: #부호와 함께 테스트 케이스 번호, 공백 후 정답 출력 (#1 1, #2 0)

6. 에이전트(agent.py) 기능 요구사항:
   - 하위 문제 디렉토리 목록 조회 (python agent.py list)
   - 하위 문제 디렉토리 자동 컴파일 및 채점 검증 (python agent.py test [문제폴더명] / --all)
   - 신규 문제 6종 규격 파일 자동 생성 및 Java 100% 검증 (python agent.py create)
   - 사용자 요구사항 명세 확인 (python agent.py spec)
=============================================================================
"""

import os
import sys
import glob
import random
import argparse
import subprocess
from collections import deque

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

REQUIRED_FILES = [
    "문제.txt",
    "sample_input.txt",
    "sample_output.txt",
    "input.txt",
    "output.txt",
    "Solution.java"
]

USER_REQUIREMENTS_TEXT = """
=============================================================================
[사용자 요구사항 명세서 - User Requirements Specification]
=============================================================================
1. 아키텍처 및 계층 구조:
   - 최상위 루트에 총괄 에이전트 파일(agent.py)이 단독으로 위치.
   - 모든 문제 디렉토리들은 반드시 agent.py 하위에서 생성되고 관리됨.
   - 문제 디렉토리 명명 규칙: [난이도]_[문제제목] (예: D3_큐_암호생성기_회전)
   - 각 문제 폴더 내부 필수 6종 파일:
     1) 문제.txt
     2) sample_input.txt (샘플 2개)
     3) sample_output.txt (샘플 정답 2개)
     4) input.txt (평가용 10개)
     5) output.txt (평가용 정답 10개)
     6) Solution.java (Java 8 SWEA 표준 솔루션)

2. 제약조건 (Java 전용):
   - 언어 제약: 오직 Java 언어로만 가능 (Java 8 이상)
   - 클래스명: 제출 시 public class Solution (패키지 선언 없음)
   - 시간 제한: 10개 테스트케이스 기준 2.0초 이내
   - 메모리 제한: 힙(Heap) 메모리 256MB 이내

3. 난이도 평가 및 주제별 균형 요건:
   - 주제별 균형 (D2, D3, D4 각 3문제 이상):
     * 각 알고리즘 및 자료구조 주제별로 D2, D3, D4가 각각 최소 3문제 이상(주제당 최소 9문제) 균형 있게 구성되어야 함.
     * 특정 주제 편향을 배제하고 난이도 편차 최소화.
   - D2 (개념 완벽 학습형):
     * 단순 알고리즘/자료구조 적용. D2 중 최소 1개는 반드시 '[주제]에 대해서' 제목으로 개념 설명, '구현하면 ~ 이렇게 된다(Trace)', '이것을 구현하라' 형식의 표준 개념 예제여야 함.
   - D3 (실전 응용 및 스토리텔링형):
     * 알고리즘/자료구조 응용. 한국 아이 대표 이름 20인(남: 이준, 도윤, 하준, 시우, 서준, 은우, 유준, 선우, 로운, 도하 / 여: 이서, 서아, 아윤, 지아, 하윤, 서윤, 시아, 아린, 나은, 유주)의 실생활 스토리텔링 기반 구현.
   - D4 (심화 및 복합 제약조건형):
     * 2~3개 이상의 복합적인 조건을 유기적으로 결합하여 해결하는 심화 문제.
   - D5 (전문가 및 삼성 B형 Pro 대비형):
     * 삼성 B형(Pro) 수준. No-STL, Zero-GC 정적 풀링, 커스텀 자료구조 직접 구현, 롤백, 비트마스킹 등 극한의 최적화.

4. 병렬 에이전트(Parallel Agent) 운용 가이드:
   - 병렬 에이전트 사용 전면 허용 및 적극 활용:
     * 대규모 문제 세트 생성, 일괄 채점/검증 시 다중 Worker Subagent를 증설하여 병렬 처리 가능.
     * 세션/주제/난이도별 분할 병렬 작업을 통해 생산성 및 속도 극대화.
   - 지속적 통합(CI) 원칙:
     * 문제 단위 생성 및 100% 테스트케이스 검증 완료 시마다 즉시 개별 `git commit` 및 `git push origin main` 실행.

5. 테스트케이스 수량:
   - 샘플: 2개
   - 백그라운드 평가용: 10개
=============================================================================
"""

# ---------------------------------------------------------------------------
# 유틸리티: 16x16 미로 생성기
# ---------------------------------------------------------------------------
def generate_base_grid(seed, n=16, loops=3):
    random.seed(seed)
    grid = [['1'] * n for _ in range(n)]
    cells = [(r, c) for r in range(1, n - 1, 2) for c in range(1, n - 1, 2)]
    visited = {cells[0]}
    stack = [cells[0]]
    grid[cells[0][0]][cells[0][1]] = '0'

    while stack:
        cr, cc = stack[-1]
        neighbors = []
        for dr, dc in [(-2, 0), (2, 0), (0, -2), (0, 2)]:
            nr, nc = cr + dr, cc + dc
            if 1 <= nr < n - 1 and 1 <= nc < n - 1 and (nr, nc) not in visited:
                neighbors.append((nr, nc, cr + dr // 2, cc + dc // 2))

        if neighbors:
            nr, nc, wr, wc = random.choice(neighbors)
            visited.add((nr, nc))
            grid[nr][nc] = '0'
            grid[wr][wc] = '0'
            stack.append((nr, nc))
        else:
            stack.pop()

    walls = []
    for r in range(2, n - 2):
        for c in range(2, n - 2):
            if grid[r][c] == '1':
                if (grid[r-1][c] == '0' and grid[r+1][c] == '0') or (grid[r][c-1] == '0' and grid[r][c+1] == '0'):
                    walls.append((r, c))
    random.shuffle(walls)
    for r, c in walls[:loops]:
        grid[r][c] = '0'

    return grid


# ---------------------------------------------------------------------------
# [기능 1] 문제 채점 및 검증 모듈
# ---------------------------------------------------------------------------
def test_problem(prob_dir_name):
    prob_dir = os.path.join(BASE_DIR, prob_dir_name)
    if not os.path.isdir(prob_dir):
        print(f"[ERROR] 디렉토리를 찾을 수 없습니다: {prob_dir}")
        return False

    print("\n" + "=" * 70)
    print(f" [채점 에이전트] 대상 문제: {prob_dir_name}")
    print("=" * 70)

    sol_java = os.path.join(prob_dir, "Solution.java")
    if not os.path.exists(sol_java):
        print(f"[ERROR] Solution.java 파일이 존재하지 않습니다: {sol_java}")
        return False

    # 1. Java 컴파일
    print("[단계 1] Solution.java 컴파일 중...")
    comp_res = subprocess.run(
        ["javac", "-encoding", "UTF-8", "Solution.java"],
        cwd=prob_dir,
        capture_output=True,
        text=True
    )
    if comp_res.returncode != 0:
        print("[FAIL] Java 컴파일 오류:\n", comp_res.stderr)
        return False
    print("  -> 컴파일 성공! (Solution.class 생성됨)")

    def run_suite(in_name, out_name, suite_title):
        in_path = os.path.join(prob_dir, in_name)
        out_path = os.path.join(prob_dir, out_name)
        if not os.path.exists(in_path) or not os.path.exists(out_path):
            print(f"  [SKIP] {in_name} 또는 {out_name} 파일 없음")
            return True

        with open(in_path, "r", encoding="utf-8") as f:
            in_text = f.read()
        with open(out_path, "r", encoding="utf-8") as f:
            expected = [line.strip() for line in f if line.strip()]

        proc = subprocess.run(
            ["java", "Solution"],
            cwd=prob_dir,
            input=in_text,
            capture_output=True,
            text=True
        )

        if proc.returncode != 0:
            print(f"  [ERROR] {suite_title} 런타임 오류:\n", proc.stderr)
            return False

        actual = [line.strip() for line in proc.stdout.splitlines() if line.strip()]
        print(f"\n[{suite_title}] 채점 결과:")
        all_passed = True
        for exp, act in zip(expected, actual):
            passed = (exp == act)
            if not passed:
                all_passed = False
            status = "PASS" if passed else "FAIL"
            print(f"  기대정답: {exp:<10} | 제출출력: {act:<10} [{status}]")

        if all_passed and len(expected) == len(actual):
            print(f"  >> {suite_title} 전체 통과 (ALL PASS)!")
            return True
        else:
            print(f"  >> {suite_title} 오답 발생 (FAIL)")
            return False

    s_ok = run_suite("sample_input.txt", "sample_output.txt", "샘플 테스트케이스 (2개)")
    e_ok = run_suite("input.txt", "output.txt", "평가용 테스트케이스 (10개)")

    # 컴파일 부산물(.class) 자동 정리
    for f in glob.glob(os.path.join(prob_dir, "*.class")):
        try: os.remove(f)
        except: pass

    final_pass = (s_ok and e_ok)
    print("\n" + "-" * 70)
    print(f" 최종 채점 결과: {'[통과 - ALL PASS]' if final_pass else '[불합격 - FAIL]'}")
    print("-" * 70)
    return final_pass


# ---------------------------------------------------------------------------
# [기능 2] 문제 디렉토리 목록 조회 모듈
# ---------------------------------------------------------------------------
def list_problems():
    print("=" * 75)
    print(" [에이전트 관리 하위 문제 목록]")
    print(" 기준 경로: " + BASE_DIR)
    print("=" * 75)

    entries = [d for d in os.listdir(BASE_DIR) if os.path.isdir(os.path.join(BASE_DIR, d))]
    prob_dirs = [d for d in entries if d.startswith("D") and "_" in d]

    if not prob_dirs:
        print("  현재 관리 중인 문제 디렉토리가 없습니다.")
        return

    print(f"{'디렉토리명':<30} | {'규격 파일 완비 여부':<20} | {'제약조건'}")
    print("-" * 75)

    for p in sorted(prob_dirs):
        p_path = os.path.join(BASE_DIR, p)
        missing = [rf for rf in REQUIRED_FILES if not os.path.exists(os.path.join(p_path, rf))]
        if not missing:
            status = "6종 파일 완비 [OK]"
        else:
            status = f"누락 ({len(missing)}개)"
        print(f"{p:<30} | {status:<20} | Java 전용")

    print("-" * 75)
    print(f"총 {len(prob_dirs)}개의 문제가 에이전트 하위에서 관리되고 있습니다.\n")


# ---------------------------------------------------------------------------
# [기능 3] 새로운 문제 생성 모듈
# ---------------------------------------------------------------------------
def create_problem(title, difficulty="D4", prob_type="security_key"):
    dir_name = f"{difficulty}_{title}"
    target_dir = os.path.join(BASE_DIR, dir_name)
    os.makedirs(target_dir, exist_ok=True)

    print("\n" + "=" * 70)
    print(f" [문제 생성 에이전트] 신규 문제 생성 시작: {dir_name}")
    print(f" 생성 경로: {target_dir}")
    print("=" * 70)

    if prob_type == "security_key":
        _create_security_key_problem(target_dir, title, difficulty)
    else:
        print(f"[ERROR] 지원하지 않는 문제 유형: {prob_type}")
        return

    print("\n[*] 6종 규격 파일 생성 완료!")
    print("[*] 에이전트 자동 채점 검증 진행...")
    test_problem(dir_name)


def _create_security_key_problem(target_dir, title, difficulty):
    def solve(maze):
        start, exit_pos, key_pos = None, None, None
        for r in range(16):
            for c in range(16):
                if maze[r][c] == '2': start = (r, c)
                elif maze[r][c] == '3': exit_pos = (r, c)
                elif maze[r][c] == '4': key_pos = (r, c)
        if not start or not exit_pos or not key_pos: return 0

        visited = [[[False] * 2 for _ in range(16)] for _ in range(16)]
        q = deque([(start[0], start[1], 0)])
        visited[start[0]][start[1]][0] = True
        dr = [-1, 1, 0, 0]; dc = [0, 0, -1, 1]

        while q:
            r, c, has_key = q.popleft()
            if (r, c) == exit_pos and has_key == 1: return 1
            for i in range(4):
                nr, nc = r + dr[i], c + dc[i]
                if not (0 <= nr < 16 and 0 <= nc < 16): continue
                tile = maze[nr][nc]
                if tile == '1': continue
                if tile == '3' and has_key == 0: continue
                n_key = 1 if (has_key == 1 or tile == '4') else 0
                if not visited[nr][nc][n_key]:
                    visited[nr][nc][n_key] = True
                    q.append((nr, nc, n_key))
        return 0

    samples = []
    cases = []

    # Sample 1 (1)
    s1 = generate_base_grid(101, 16, loops=5)
    s1[1][1] = '2'; s1[7][7] = '4'; s1[13][13] = '3'
    samples.append(["".join(r) for r in s1])

    # Sample 2 (0)
    s2 = generate_base_grid(202, 16, loops=2)
    s2[1][1] = '2'; s2[7][7] = '4'; s2[13][13] = '3'
    s2[6][7] = '1'; s2[8][7] = '1'; s2[7][6] = '1'; s2[7][8] = '1'
    samples.append(["".join(r) for r in s2])

    # Case 1 (1)
    c1 = generate_base_grid(303, 16, loops=4)
    c1[1][1] = '2'; c1[7][13] = '4'; c1[13][13] = '3'
    cases.append(["".join(r) for r in c1])

    # Case 2 (0)
    c2 = generate_base_grid(404, 16, loops=3)
    c2[1][1] = '2'; c2[7][7] = '4'; c2[13][13] = '3'
    c2[12][13] = '1'; c2[14][13] = '1'; c2[13][12] = '1'; c2[13][14] = '1'
    cases.append(["".join(r) for r in c2])

    # Case 3 (0) - Exit blocks key corridor
    c3 = [['1'] * 16 for _ in range(16)]
    for c in range(1, 15): c3[1][c] = '0'
    c3[1][1] = '2'; c3[1][7] = '3'; c3[1][13] = '4'
    cases.append(["".join(r) for r in c3])

    # Case 4 (1) - Snake
    c4 = [['1'] * 16 for _ in range(16)]
    for r in range(1, 14, 2):
        for c in range(1, 15): c4[r][c] = '0'
        if r + 1 < 14:
            if (r // 2) % 2 == 0: c4[r+1][14] = '0'
            else: c4[r+1][1] = '0'
    c4[1][1] = '2'; c4[7][1] = '4'; c4[13][14] = '3'
    cases.append(["".join(r) for r in c4])

    # Case 5 (1) - Rooms
    c5 = [['1'] * 16 for _ in range(16)]
    for r in range(1, 5):
        for c in range(1, 5): c5[r][c] = '0'
    for r in range(10, 15):
        for c in range(10, 15): c5[r][c] = '0'
    for r in range(1, 6):
        for c in range(10, 15): c5[r][c] = '0'
    for r in range(9, 15):
        for c in range(1, 6): c5[r][c] = '0'
    for c in range(4, 11): c5[3][c] = '0'
    for r in range(3, 11): c5[r][12] = '0'
    for c in range(4, 11): c5[12][c] = '0'
    for r in range(4, 10): c5[r][3] = '0'
    c5[2][2] = '2'; c5[12][2] = '4'; c5[12][12] = '3'
    cases.append(["".join(r) for r in c5])

    # Case 6 (0) - Start blocked
    c6 = generate_base_grid(505, 16)
    c6[1][1] = '2'; c6[7][7] = '4'; c6[13][13] = '3'
    c6[0][1] = '1'; c6[2][1] = '1'; c6[1][0] = '1'; c6[1][2] = '1'
    cases.append(["".join(r) for r in c6])

    # Case 7 (1) - Start & Exit near, key far
    c7 = generate_base_grid(606, 16, loops=3)
    c7[1][1] = '2'; c7[1][3] = '3'; c7[1][2] = '1'
    c7[2][1] = '0'; c7[2][3] = '0'; c7[13][13] = '4'
    cases.append(["".join(r) for r in c7])

    # Case 8 (0) - Bisected
    c8 = generate_base_grid(707, 16)
    for c in range(16): c8[8][c] = '1'
    c8[1][1] = '2'; c8[13][13] = '4'; c8[3][13] = '3'
    cases.append(["".join(r) for r in c8])

    # Case 9 (1) - Complex
    c9 = generate_base_grid(808, 16, loops=6)
    c9[1][1] = '2'; c9[5][11] = '4'; c9[13][13] = '3'
    cases.append(["".join(r) for r in c9])

    # Case 10 (1) - High branching
    c10 = generate_base_grid(909, 16, loops=5)
    for r in [3, 7, 11]:
        for c in [3, 7, 11]: c10[r][c] = '0'
    c10[1][1] = '2'; c10[7][7] = '4'; c10[13][13] = '3'
    cases.append(["".join(r) for r in c10])

    p_text = f"""[S/W 문제해결 응용] {title.replace('_', ' ')} ({difficulty})

16×16 크기의 연구소 미로가 있다. 연구소의 경계와 장애물은 벽으로 둘러싸여 있으며, 연구원은 시작점에서 출발하여 잠겨 있는 비상 탈출구로 탈출해야 한다.

이번 연구소에는 비상 보안 시스템이 가동되어 반드시 '보안 카드키'를 먼저 획득한 후에만 잠긴 비상 탈출구(3)에 진입할 수 있다. 
(※ 주의: 보안 카드키를 획득하기 전에는 비상 탈출구 칸으로 이동하거나 통과할 수 없다. 즉, 카드키 미소지 시 탈출구는 벽처럼 이동이 차단된다.)

주어진 미로에서 시작점으로부터 카드키를 획득하고, 최종적으로 비상 탈출구까지 도달할 수 있는지 판단하는 프로그램을 작성하라.


[제약 사항 (Java 전용)]
1. 언어 제한: 본 문제는 Java 언어로만 작성 및 제출이 가능합니다. (Java 8 이상 지원)
2. 클래스명: 제출 시 클래스 이름은 반드시 Solution이어야 합니다. (패키지명 없이 제출)
3. 시간 제한: 10개 테스트 케이스 기준 2.0초 이내 (각 테스트 케이스당 0.2초 이내)
4. 메모리 제한: 힙(Heap) 메모리 256MB 이내
5. 미로의 크기는 가로 16, 세로 16 (16×16) 고정이다.
6. 미로의 가장 바깥 테두리(0행, 15행, 0열, 15열)는 항상 벽(1)이다.
7. 이동은 상, 하, 좌, 우 4방향으로만 가능하며 대각선 이동은 불가능하다.
8. 연구원의 시작 위치(2), 잠긴 비상 탈출구(3), 보안 카드키(4)는 미로 내에 각각 정확히 1개씩 존재하며 서로 다른 위치에 배치된다.
9. 카드키를 획득한 이후에는 이전에 지나온 길을 다시 되돌아갈 수 있다.


[격자 구성]
  0: 이동 가능한 통로
  1: 벽 (이동 불가)
  2: 연구원의 시작 위치 (출발점)
  3: 잠긴 비상 탈출구 (도착점, 카드키 획득 전 진입/통과 불가)
  4: 보안 카드키 위치 (1개 존재)


[입력]
각 테스트 케이스의 첫 번째 줄에는 테스트 케이스의 번호가 주어지며, 바로 다음 줄부터 16개의 줄에 걸쳐 각 줄마다 길이 16인 문자열 형태로 미로의 정보가 주어진다.
샘플 테스트 케이스(sample_input.txt)는 총 2개이며,
백그라운드 평가용 테스트 케이스(input.txt)는 총 10개가 주어진다.


[출력]
각 테스트 케이스마다 '#' 부호와 함께 테스트 케이스의 번호를 출력하고, 공백 후 탈출 가능하면 1, 불가능하면 0을 출력한다.
"""
    with open(os.path.join(target_dir, "문제.txt"), "w", encoding="utf-8") as f:
        f.write(p_text)

    with open(os.path.join(target_dir, "sample_input.txt"), "w", encoding="utf-8") as f_in, \
         open(os.path.join(target_dir, "sample_output.txt"), "w", encoding="utf-8") as f_out:
        for tc, m in enumerate(samples, 1):
            f_in.write(f"{tc}\n")
            for row in m: f_in.write(f"{row}\n")
            f_out.write(f"#{tc} {solve(m)}\n")

    with open(os.path.join(target_dir, "input.txt"), "w", encoding="utf-8") as f_in, \
         open(os.path.join(target_dir, "output.txt"), "w", encoding="utf-8") as f_out:
        for tc, m in enumerate(cases, 1):
            f_in.write(f"{tc}\n")
            for row in m: f_in.write(f"{row}\n")
            f_out.write(f"#{tc} {solve(m)}\n")

    sol_java_code = """import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SW Expert Academy
 * 제출 언어: Java (Java 8 이상)
 * 알고리즘: 상태 공간 BFS (3D State BFS - visited[r][c][hasKey])
 */
public class Solution {

    static class State {
        int r, c, hasKey;
        public State(int r, int c, int hasKey) {
            this.r = r;
            this.c = c;
            this.hasKey = hasKey;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String tc = line;
            char[][] map = new char[16][16];

            int startR = -1, startC = -1;
            int exitR = -1, exitC = -1;
            int keyR = -1, keyC = -1;

            for (int r = 0; r < 16; r++) {
                String row = br.readLine().trim();
                for (int c = 0; c < 16; c++) {
                    map[r][c] = row.charAt(c);
                    if (map[r][c] == '2') {
                        startR = r; startC = c;
                    } else if (map[r][c] == '3') {
                        exitR = r; exitC = c;
                    } else if (map[r][c] == '4') {
                        keyR = r; keyC = c;
                    }
                }
            }

            if (startR == -1 || exitR == -1 || keyR == -1) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            boolean[][][] visited = new boolean[16][16][2];
            Queue<State> queue = new ArrayDeque<>();

            queue.offer(new State(startR, startC, 0));
            visited[startR][startC][0] = true;

            int escaped = 0;

            while (!queue.isEmpty()) {
                State cur = queue.poll();

                if (cur.r == exitR && cur.c == exitC && cur.hasKey == 1) {
                    escaped = 1;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nr < 0 || nr >= 16 || nc < 0 || nc >= 16) continue;

                    char tile = map[nr][nc];
                    if (tile == '1') continue;
                    if (tile == '3' && cur.hasKey == 0) continue;

                    int nextHasKey = (cur.hasKey == 1 || tile == '4') ? 1 : 0;

                    if (!visited[nr][nc][nextHasKey]) {
                        visited[nr][nc][nextHasKey] = true;
                        queue.offer(new State(nr, nc, nextHasKey));
                    }
                }
            }

            System.out.println("#" + tc + " " + escaped);
        }
    }
}
"""
    with open(os.path.join(target_dir, "Solution.java"), "w", encoding="utf-8") as f:
        f.write(sol_java_code)


# ---------------------------------------------------------------------------
# 메인 CLI 인터페이스
# ---------------------------------------------------------------------------
def main():
    parser = argparse.ArgumentParser(description="SWEA 코딩테스트 문제 관리 및 생성 AI 에이전트")
    subparsers = parser.add_subparsers(dest="command", help="실행할 명령어")

    # 1. spec (사용자 요구사항 확인)
    subparsers.add_parser("spec", help="사용자 요구사항 명세서 출력")

    # 2. list
    subparsers.add_parser("list", help="에이전트가 관리 중인 문제 디렉토리 목록 조회")

    # 3. test
    test_parser = subparsers.add_parser("test", help="하위 문제 디렉토리 자동 컴파일 및 채점")
    test_parser.add_argument("problem", nargs="?", default=None, help="채점할 문제 디렉토리명")
    test_parser.add_argument("--all", action="store_true", help="모든 문제 일괄 채점")

    # 4. create
    create_parser = subparsers.add_parser("create", help="신규 문제 디렉토리 및 6종 파일 세트 자동 생성")
    create_parser.add_argument("--title", required=True, help="문제 제목 (예: 화물_보안_탈출)")
    create_parser.add_argument("--difficulty", default="D4", help="난이도 (D1~D5, 기본값: D4)")
    create_parser.add_argument("--type", default="security_key", choices=["security_key"], help="문제 유형")

    args = parser.parse_args()

    if args.command == "spec":
        print(USER_REQUIREMENTS_TEXT)
    elif args.command == "list" or args.command is None:
        list_problems()
    elif args.command == "test":
        if args.all:
            prob_dirs = sorted([d for d in os.listdir(BASE_DIR) if os.path.isdir(os.path.join(BASE_DIR, d)) and d.startswith("D") and "_" in d])
            for pd in prob_dirs:
                test_problem(pd)
        elif args.problem:
            test_problem(args.problem)
        else:
            print("[ERROR] 채점할 문제 디렉토리명을 지정하거나 --all 옵션을 사용하세요.")
    elif args.command == "create":
        create_problem(args.title, args.difficulty, args.type)


if __name__ == "__main__":
    main()
