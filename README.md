# Welcome to `kotlin-multi-datasource`

## What is repository
本リポジトリはKotlin x SpringBootでDB接続のRW/ROを使い分けたい時の実装サンプルコードです

## 主要技術
| 名称         | バージョン               | 用途                       |
|------------|---------------------|--------------------------|
| OpenJDK    | 21(Amazon Corretto) | サーバ開発キット                 |
| Kotlin     | 1.9.22              | サーバ言語                    |
| SpringBoot | 3.2.3               | サーバFW                    |
| MySQL      | 8.0.33              | RDB                      |

## Project architecture
本プロジェクトはマルチプロジェクト構成を採用しています

## Advance preparation
以下をPCにインストール&準備をする必要があります(macOS/zshでの開発が前提となります)
1. OrbStack ※ライセンスがある場合はDockerDesktop
2. IntelliJ

#### 1.OrbStack install
https://orbstack.dev/download

#### 2.IntelliJ install
https://www.jetbrains.com/ja-jp/idea/download/#section=mac

#### 3.JDK setup
主要技術に記載されているJDKを設定します  
以下の記事を参考にしてください  
https://pleiades.io/help/idea/sdk.html#set-up-jdk

#### 4.Docker image build
```
% make docker-build
% make run
```

## Main
本実装のポイントは以下となります  
1. DBのRW/ROの接続先をapplication.yamlに定義し、[DataSourceConfig](./common/src/main/kotlin/seki/kotlin/base/common/config/db/DbConfig.kt)を定義する  
2. メソッド単位でTransactionalアノテーションを指定し、RO接続がしたい時のみ「readOnly = true」を付加する[SampleService.kt](./api/src/main/kotlin/seki/kotlin/base/api/service/SampleService.kt)

## Access point
ローカル開発での生産性向上のため、必要なミドルウェアはコンテナ化しています  
各種ポート設定は以下となっています    
| アプリ | URL |
| ---- | ---- |
| API | http://localhost:8080 |
| DB | http://localhost:23306 |
