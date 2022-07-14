# jupyter-book 간단 사용법
- 원본 문서: https://jupyterbook.org/start/overview.html

(start:install)=
## 1. Install Jupyter Book
- 필요한 모듈 설치
- You can install Jupyter Book [via `pip`](https://pip.pypa.io/en/stable/):
- python이 설치되어 있으면 pip 실행가능
  - AMF교육시 "pip install httpie"가 수행되는 환경이면 됨
  ```bash
  pip install -U jupyter-book
  ```
- python 버전에 따라 많은 문제 발생(공식적으로는  python 3.7을 지원함)
- 현 테스트는 python 3.9에서 수행에 특이사항 없음

## 2. The Jupyter Book command-line interface

아래 명령어가 수행되면 정상 작동
```bash
jupyter-book --help

또는
jb --help  # JB는 축약형
```

```{code-cell}
:tags: [remove-input]

!jupyter-book --help
```


## 3 생성절차
doc 아래에 있는 문서를 HTML 형식으로 일괄 변경하기

```console
build.cmd
```
build내용및 결과 확인
```console
chcp 65001

title "jupyter book 만들기"

# 생성위치는 각자 수정(d:\APP\GIT-AMF3\backend\doc\htm)
# 해당 shell 수행위치는 build.msa가 있는 위치에서 수행
jb build  --path-output "d:\APP\GIT-AMF3\backend\doc\html"  .\
```


