(defproject jiraclient "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["atlassian" "https://maven.atlassian.com/repository/public"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [com.atlassian.jira/jira-rest-java-client "0.6-m9"]]
  :plugins [[lein-swank "1.4.4"]]
  :main jiraclient.core)

