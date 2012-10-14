(ns jiraclient.core
  (:import (java.awt SystemTray Toolkit PopupMenu TrayIcon)))

(def image (-> (Toolkit/getDefaultToolkit) 
             (.getImage "favicon.png")))

(def menu (PopupMenu.))
(def tray-icon (TrayIcon. image "JIRA client" menu))
(defn -main [& args]
  (-> (SystemTray/getSystemTray)
    (.add tray-icon)))

