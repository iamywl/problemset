# 🧩 SWEA Java 알고리즘 문제 세트 & AI 채점 에이전트

SW Expert Academy (SWEA) 스타일의 코딩테스트 문제 세트와, 문제를 통합 관리·채점·생성하는 AI 에이전트 시스템입니다.

---

## 📌 주요 특징

1. **Java 전용 제약조건 준수**:
   - 모든 문제는 **Java (Java 8 이상)** 기준
   - 클래스명 `Solution` (`public class Solution`)
   - 10개 테스트케이스 기준 2.0초 이내, 힙 메모리 256MB 이내

2. **표준 6종 파일 규격**:
   - `문제.txt`: 문제 명세서, 제약사항, 입출력 포맷
   - `sample_input.txt` & `sample_output.txt`: 샘플 테스트케이스 (2개)
   - `input.txt` & `output.txt`: 백그라운드 평가용 테스트케이스 (10개)
   - `Solution.java`: 검증 완료된 Java 8 표준 솔루션

3. **통합 관리 AI 에이전트 (`agent.py`)**:
   - 에이전트 파일 하나로 하위 모든 문제 디렉토리를 일괄 컴파일, 테스트케이스 채점, 신규 생성 관리

---

## 📂 프로젝트 구조

```text
SWEAProblemSet/
│
├── agent.py                            <-- 코딩테스트 문제 총괄 관리/채점 AI 에이전트
├── .gitignore                          <-- 불필요한 바이너리(.class 등) 제외 설정
├── README.md                           <-- 프로젝트 문서
│
├── D2_BFS_미로_탈출_기초/
├── D3_BFS_바이러스_확산_시간/
├── D3_BFS_숨바꼭질_워프/
├── D3_BFS_안전_영역_카운트/
├── D4_BFS_미로_최단경로/
├── D4_BFS_연구소_보안탈출/
├── D4_BFS_일방통행_연구소_탈출/          <-- 사용자 작성 알고리즘 기반 문제
├── D4_BFS_벽_부수고_이동하기/
├── D4_BFS_불과_비상탈출/
├── D4_BFS_토마토_숙성_창고/
├── D5_BFS_보물섬_열쇠_수집/
│
├── D2_DFS_연결_컴포넌트/
├── D2_DFS_단지_번호_붙이기/
├── D3_DFS_바이러스_감염_컴퓨터/
├── D3_DFS_경로의_개수_찾기/
├── D3_DFS_음식물_피하기/
├── D4_DFS_알파벳_보드_여행/
├── D4_DFS_치즈_외부공기_녹이기/
├── D4_DFS_등산로_조성/
├── D5_DFS_N_Queen_배치/
└── D5_DFS_연구소_방화벽_구축/
```

---

## 🚀 에이전트(`agent.py`) 실행 방법

### 1. 문제 목록 확인
```bash
python agent.py list
```

### 2. 전체 문제 일괄 컴파일 및 자동 채점
```bash
python agent.py test --all
```

### 3. 특정 문제 단독 채점
```bash
python agent.py test D4_BFS_연구소_보안탈출
```

### 4. 사용자 요구사항 명세 확인
```bash
python agent.py spec
```
