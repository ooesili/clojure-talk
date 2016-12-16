(ns user
  (:require [clojure.repl :refer :all]
            [clojure.tools.namespace.repl :refer [refresh]]
            [user.state :refer [state]]
            [com.stuartsierra.component :as component]
            [clojure-talk.core :as core]
            [clojure-talk.http-server :refer [http-server]]))

(defn stop []
  (when-let [server (:server @state)]
    (component/stop server)
    (swap! state dissoc :server))
  :stopped)

(defn start-thunk []
  (swap! state assoc :server
         (component/start (http-server core/handler)))
  :ok)

(defn start []
  (stop)
  (refresh :after 'user/start-thunk))
