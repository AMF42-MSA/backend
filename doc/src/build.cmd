rem Code Page변경 : MS949 -> UTF-8
chcp 65001

title "jupyter book 만들기"

rem 생성위치는 각자 수정(d:\APP\GIT-AMF3\backend\doc\htm)
rem 해당 shell 수행위치는 build.msa가 있는 위치에서 수행
jb build  --path-output "d:\APP\GIT-AMF3\backend\doc\html"  .\

REM 기존 문서와 동일위치에 생성하기 위하여 추가 빌드
#jb build  --path-output "c:\APP\jb_html\MSA-EDU-LV3"  --all .\



rem 만약 목차가 많이 수정되거나, "다음", "이전"이 잘못 연결되면 "--all" option 추가하여 빌드
rem jb build  --all --path-output "d:\APP\GIT-AMF3\backend\doc\html"  .\
