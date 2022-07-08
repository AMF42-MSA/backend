# 00-환경 설정
- ![원자료azure-spring-cloud-training](https://github.com/microsoft/azure-spring-cloud-training)
- 다른 부분을 실행하기 전에 꼭 실행 필요함
---

## 1.Azure 리소스 만들기
To save time, 실습에 필요한 모든 Azure 리소스를 만들기 위한 ARM 템플릿을 제공

> 💡Azure 템플릿 배포에 다음 설정을 사용:
>
> * 리소스 그룹 생성.
> * [Azure Spring Apps를 사용할 수 있는 지역 목록](https://azure.microsoft.com/global-infrastructure/services/?products=spring-apps&regions=all)가장 가까운 지역을 선택
>    * Korea Central
>    * Korea South 는 일부 서비시 지원하지 않는 부분 있음
> * MySQL password 여기서 저장.
>   * 6 chap에서 사용, 미 설정하면 `super$ecr3t`.

[![Deploy to Azure](media/deploybutton.svg)](https://portal.azure.com/#create/Microsoft.Template/uri/https%3A%2F%2Fraw.githubusercontent.com%2Fmicrosoft%2Fazure-spring-cloud-training%2Fmaster%2F00-setup-your-environment%2Fazuredeploy.json?WT.mc_id=azurespringcloud-github-judubois)

>⏱ 리소스 프로비저닝에는 시간이 걸립니다. 기다리지 마! 워크샵을 계속합니다.

## 2.전제 조건

PC 설치 필요내용:

* [JDK 17](https://docs.microsoft.com/java/openjdk/download?WT.mc_id=azurespringcloud-github-judubois#openjdk-17)
* A text editor or an IDE.
  * [Visual Studio Code](https://code.visualstudio.com/?WT.mc_id=azurespringcloud-github-judubois) with the [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack&WT.mc_id=azurespringcloud-github-judubois).

* The Bash shell
  * Windows에서 이 교육을 완료하려면 Git Bash만 사용하십시오. WSL, CloudShell 또는 기타 셸을 사용하지 마십시오(https://git-scm.com/download/win)

* [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest&WT.mc_id=azurespringcloud-github-judubois) version 2.31.0 or later
  ```bash
  az --version
  ```

> 💡 위 명령에서  `bash: az: command not found`와 같은 오류가 발생하면
>   -  `alias az='az.cmd'`

* 🚧 추거 확장 설치  `az extension add -n spring -y`.
  * 기 설치되었으면 Upgrade: `az extension update -n spring`.

> 💡 9,10에서는 WEB으로 접속 [new Edge](https://microsoft.com/edge/?WT.mc_id=azurespringcloud-github-judubois), Google Chrome, or Firefox

- 환경변수 설정:  `JAVA_HOME`
- `PATH` : `${JAVA_HOME}/bin`.
  - To test, type `which javac` into bash shell  ==> path에 설정된 값이 나오는지 확인

---

➡️ Next guide: [01 - Create an Azure Spring Apps instance](./01-create-an-azure-spring-cloud-instance.md)
