rem Code Page변경 : MS949 -> UTF-8
chcp 65001

title "jupyter book 만들기"

#set HTML_ROOT_DIR=c:\APP\jb_html

jb build  --path-output "d:\APP\GIT-AMF3\backend\doc\html"  .\
