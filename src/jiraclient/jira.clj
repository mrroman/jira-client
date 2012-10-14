(ns jiraclient.jira
  (:import
   (com.atlassian.jira.rest.client.internal.jersey JerseyJiraRestClientFactory)
   (com.atlassian.jira.rest.client.auth AnonymousAuthenticationHandler)
   (com.atlassian.jira.rest.client ProgressMonitor)))

(def rest-client-factory (JerseyJiraRestClientFactory.))

(def anonymous-auth-handler (com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler.))

(def progress-monitor (proxy [ProgressMonitor] []))

(defn create-jira-client [url & opts]
  "Creates JIRA client. First argument is URL of JIRA server. When you specify :handler it will be used to authenticate of server. If not, you must specify :username and :password for HTTP Basic Authentication"
  (let [opts-map (apply hash-map opts)
        uri (java.net.URI. url)]
    (if (contains? opts-map :handler)
      (.create rest-client-factory uri (:handler opts-map))
      (.createWithBasicHttpAuthentication rest-client-factory uri (:username opts-map) (:password opts-map)))))

(defn get-issue [jira-client ticket-id]
  "Get issue object from JIRA"
  (let [issue-client (.getIssueClient jira-client)]
    (.getIssue issue-client ticket-id progress-monitor)))

(defn search-issues [jira-client jira-query]
  "Search for issues"
  (let [search-client (.getSearchClient jira-client)]
    (-> (.searchJql search-client jira-query progress-monitor)
        (.getIssues))))