# Jenkinsfile Example
- https://www.jenkins.io/doc/pipeline/examples/

## Ansi Color Build Wrapper
이것은 AnsiColor 플러그인을 사용하는 간단한 빌드 래퍼 예제를 보여줍니다.

- ansiColor plugin이 설치되어 있어야 함
```bash
// 이것은 AnsiColor 플러그인을 사용하는 간단한 빌드 래퍼 예제를 보여줍니다.
// This shows a simple build wrapper example, using the AnsiColor plugin.
node {
    // This displays colors using the 'xterm' ansi color map.
    ansiColor('xterm') {
        // Just some echoes to show the ANSI color.
        stage "\u001B[31mI'm Red\u001B[0m Now not"
    }
}

```
```bash
pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
    stages {
        stage('Build') {
            steps {
                echo '\033[34mHello\033[0m \033[33mcolorful\033[0m \033[35mworld!\033[0m'
            }
        }
    }
}
```
## Archive Build Output Artifacts
```bash
// 이것은 빌드 출력 아티팩트를 아카이브하는 방법의 간단한 예를 보여줍니다.
node {
    stage "Create build output"

     // 출력 디렉토리를 만듭니다.
    sh "mkdir -p output"

     // 아카이브에 필요한 유용한 파일을 작성합니다.
    writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."

     // 보관할 필요가 없는 쓸모없는 파일을 작성합니다.
    writeFile file: "output/uselessfile.md", text: "This file is useless, no need to archive it."

    stage "Archive build output"

     // 빌드 출력 아티팩트를 아카이브합니다.
    archiveArtifacts artifacts: 'output/*.txt', excludes: 'output/*.md'
}
```

## Artifactory Generic Upload Download
종속성을 다운로드하고, 아티팩트를 업로드하고, Artifactory에 빌드 정보를 게시하는 방법에 대한 간단한 데모
- https://www.jfrog.com/confluence/display/JFROG/Working+With+Pipeline+Jobs+in+Jenkins
```bash
node {
    git url: 'https://github.com/jfrogdev/project-examples.git'

    // Artifactory Plugin 관리 페이지에 정의된 Artifactory 서버 인스턴스를 가져옵니다.
    def server = Artifactory.server "SERVER_ID"

    // 업로드 사양을 읽고 Artifactory에 파일을 업로드합니다.
    def downloadSpec =
            '''{
            "files": [
                {
                    "pattern": "libs-snapshot-local/*.zip",
                    "target": "dependencies/",
                    "props": "p1=v1;p2=v2"
                }
            ]
        }'''

    def buildInfo1 = server.download spec: downloadSpec

    // github에서 다운로드한 업로드 사양을 읽습니다..
    def uploadSpec =
            '''{
            "files": [
                {
                    "pattern": "resources/Kermit.*",
                    "target": "libs-snapshot-local",
                    "props": "p1=v1;p2=v2"
                },
                {
                    "pattern": "resources/Frogger.*",
                    "target": "libs-snapshot-local"
                }
            ]
        }'''

    // Artifactory에 업로드합니다.
    def buildInfo2 = server.upload spec: uploadSpec

    // 업로드 및 다운로드 빌드 정보 개체를 병합
    buildInfo1.append buildInfo2

    // Artifactory에 빌드를 게시
    server.publishBuildInfo buildInfo1
}
```
```bash

```
## Artifactory Gradle Build
```bash

```
```bash

```
## Artifactory Maven Build
종속성을 해결하고 아티팩트를 업로드하고 Artifactory에 빌드 정보를 게시하는 Maven 빌드를 실행하는 방법에 대한 간단한 데모

https://www.jfrog.com/confluence/display/JFROG/Working+With+Pipeline+Jobs+in+Jenkins

```bash
node {
    // Artifactory Plugin 관리 페이지에 정의된 Artifactory 서버 인스턴스를 가져옵니다.
    def server = Artifactory.server "SERVER_ID"
    // Create an Artifactory Maven instance.
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage('Clone sources') {
        git url: 'https://github.com/jfrogdev/project-examples.git'
    }

    stage('Artifactory configuration') {
       // 도구 Jenkins 구성의 이름
        rtMaven.tool = "Maven-3.3.9"
        // 종속성 해결 및 아티팩트 배포를 위한 Artifactory 저장소를 설정
        rtMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: server
    }
ㄴ
    stage('Maven build') {
        buildInfo = rtMaven.run pom: 'maven-example/pom.xml', goals: 'clean install'
    }

    stage('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}
```
```bash

```
## Configfile Provider Plugin
```bash

```
```bash

```
## External Workspace Manager
```bash

```
```bash

```
## Get Build Cause
```bash

```
```bash

```
## Gitcommit
```bash

```
```bash

```
## Gitcommit_changeset
```bash

```
```bash

```
## Ircnotify Commandline
```bash

```
```bash

```
## Jobs In Parallel
```bash

```
```bash

```
## Load From File
```bash

```
```bash

```
## Maven And Jdk Specific Version
```bash

```
```bash

```
## Parallel From Grep
```bash

```
```bash

```
## Parallel From List
```bash

```
```bash

```
## Parallel Multiple Nodes
```bash

```
```bash

```
## Push Git Repo
```bash

```
```bash

```
## Slacknotify
```bash

```
```bash

```
## Timestamper Wrapper
```bash

```
```bash

```
## Trigger Job On All Nodes
```bash

```
```bash

```
## Unstash Different Dir
```bash

```
```bash
java.lang.NoSuchMethodError: No such DSL method 'ansiColor' found among steps

archive,
bat,
build,
catchError,
checkout,
deleteDir,
dir,
dockerFingerprintFrom,
dockerFingerprintRun,
echo,
emailext,
emailextrecipients,
envVarsForTool,
error, fileExists, findBuildScans, getContext, git, input, isUnix, junit, library, libraryResource, load, mail, milestone, node, parallel, powershell, properties, publishChecks, publishHTML, pwd, pwsh, readFile, readTrusted, resolveScm, retry, script, sh, sleep, stage, stash, step, timeout, timestamps, tm, tool, unarchive, unstable, unstash, validateDeclarativePipeline, waitUntil, warnError, withChecks, withContext, withCredentials, withDockerContainer, withDockerRegistry, withDockerServer, withEnv, withGradle, withMaven, wrap, writeFile, ws] or symbols [GitUsernamePassword, agent, all, allBranchesSame, allOf, always, ant, antFromApache, antOutcome, antTarget, any, anyOf, apiToken, apiTokenProperty, architecture, archiveArtifacts, artifactManager, artifactsPublisher, authorizationMatrix, batchFile, bitbucket, bitbucketBranchDiscovery, bitbucketBuildStatusNotifications, bitbucketForkDiscovery, bitbucketPublicRepoPullRequestFilter, bitbucketPullRequestDiscovery, bitbucketServer, bitbucketSshCheckout, bitbucketTagDiscovery, bitbucketTrustEveryone, bitbucketTrustNobody, bitbucketTrustProject, bitbucketTrustTeam, bitbucketWebhookConfiguration, bitbucketWebhookRegistration, booleanParam, branch, brokenBuildSuspects, brokenTestsSuspects, buildButton, buildDiscarder, buildDiscarders, buildRetention, buildUser, buildingTag, builtInNode, caseInsensitive, caseSensitive, certificate, changeRequest, changelog, changeset, checkoutToSubdirectory, choice, choiceParam, cleanWs, clock, command, concordionPublisher, configFile, configFileProvider, contributor, credentials, cron, crumb, culprits, defaultFolderConfiguration, defaultView, demand, dependenciesFingerprintPublisher, developers, disableConcurrentBuilds, disableResume, docker, dockerCert, dockerServer, dockerTool, dockerfile, downstream, dumb, durabilityHint, email-ext, envVars, envVarsFilter, environment, equals, expression, extendedEmailPublisher, file, fileParam, filePath, findbugsPublisher, fingerprint, fingerprints, frameOptions, freeStyle, freeStyleJob, fromDocker, fromScm, fromSource, git, gitBranchDiscovery, gitHubBranchDiscovery, gitHubBranchHeadAuthority, gitHubExcludeArchivedRepositories, gitHubExcludeForkedRepositories, gitHubExcludePrivateRepositories, gitHubExcludePublicRepositories, gitHubForkDiscovery, gitHubIgnoreDraftPullRequestFilter, gitHubPullRequestDiscovery, gitHubSshCheckout, gitHubTagDiscovery, gitHubTopicsFilter, gitHubTrustContributors, gitHubTrustEveryone, gitHubTrustNobody, gitHubTrustPermissions, gitTagDiscovery, gitUsernamePassword, github, githubProjectProperty, githubPush, globalConfigFiles, gradle, headRegexFilter, headWildcardFilter, hyperlink, hyperlinkToModels, inheriting, inheritingGlobal, installSource, invokerPublisher, isRestartedRun, jacocoPublisher, javadoc, jdk, jdkInstaller, jgit, jgitapache, jgivenPublisher, jnlp, jobBuildDiscarder, jobName, junitPublisher, junitTestResultStorage, label, lastDuration, lastFailure, lastGrantedAuthorities, lastStable, lastSuccess, legacy, legacySCM, list, local, location, logRotator, loggedInUsersCanDoAnything, mailer, masterBuild, maven, maven3Mojos, mavenErrors, mavenGlobalConfig, mavenLinkerPublisher, mavenMojos, mavenWarnings, modernSCM, myView, namedBranchesDifferent, newContainerPerStage, node, nodeProperties, nonInheriting, none, nonresumable, not, openTasksPublisher, organizationFolder, overrideIndexTriggers, paneStatus, parallelsAlwaysFailFast, parameters, password, pattern, permanent, pipeline, pipeline-model, pipeline-model-docker, pipelineGraphPublisher, pipelineMaven, pipelineTriggers, plainText, plugin, pollSCM, preserveStashes, previous, projectNamingStrategy, proxy, pruneTags, queueItemAuthenticator, quietPeriod, rateLimit, rateLimitBuilds, recipients, requestor, resourceRoot, retainOnlyVariables, run, runParam, sSHLauncher, schedule, scmRetryCount, scriptApproval, scriptApprovalLink, search, security, shell, simpleBuildDiscarder, skipDefaultCheckout, skipStagesAfterUnstable, slave, snapshotDependencies, sourceRegexFilter, sourceWildcardFilter, spotbugsPublisher, ssh, sshPublicKey, sshUserPrivateKey, standard, status, string, stringParam, suppressAutomaticTriggering, suppressFolderAutomaticTriggering, swapSpace, tag, teamSlugFilter, text, textParam, timestamper, timestamperConfig, timezone, tmpSpace, toolLocation, triggeredBy, unsecured, untrusted, upstream, upstreamDevelopers, userSeed, usernameColonPassword, usernamePassword, viewsTabBar, weather, withAnt, x509ClientCert, zip] or globals [currentBuild, docker, env, params, pipeline, scm]
```
