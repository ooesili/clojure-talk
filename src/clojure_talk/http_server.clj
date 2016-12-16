(ns clojure-talk.http-server
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :refer [run-server]]))

(defrecord HTTPServer [handler stop-server port]
  component/Lifecycle
  (start [this]
    (assoc this :stop-server
           (run-server handler {:port port})))
  (stop [this]
    (stop-server)
    (dissoc this :stop-server)))

(defn http-server [handler]
  (map->HTTPServer {:port 3000
                    :handler handler}))
