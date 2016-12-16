(defproject clojure-talk "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [hiccup "1.0.5"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]]
  :profiles
  {:dev {:source-paths ["dev" "src"]
         :dependencies [[org.clojure/tools.namespace "0.2.11"]]}})
