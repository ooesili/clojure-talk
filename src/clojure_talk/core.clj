(ns clojure-talk.core
  (:require [compojure.core :refer :all]
            [hiccup.page :refer [html5 include-js include-css]]
            [ring.util.response :as response]
            [com.stuartsierra.component :as component]
            [clojure.string :as string]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]))

(defn code [& lines]
  [:pre
   [:code (string/join "\n" lines)]])

(defn comic [lang]
  [:img {:src (str "/img/comic-" lang ".png")}])

(def slides
  [:div.slides
   [:section
    [:section
     [:h1 "Clojure"]
     [:h2 "(embrace the parenthesis)"]]]

   [:section
    [:section [:h2 "practical + functional"]]
    [:section (comic "java")]
    [:section [:h1 "+"]]
    [:section (comic "haskell")]
    [:section [:h1 "="]]
    [:section [:img.logo {:src "/img/clj-logo.png"}]]]

   [:section
    [:section
     [:h2 "Clojure"]
     [:blockquote
      "\"Clojure is a dynamic, general-purpose programming language, combining
      the approachability and interactive development of a scripting language
      with an efficient and robust infrastructure for multithreaded
      programming.\""]]
    [:section
     [:h3 "\"predominantly a functional language\""]
     [:p "more focused on data and computation than objects and interactions"]
     [:p "heavily influenced by the lambda calculus"]]
    [:section
     [:h3 "the Lisp family"]
     [:p "Lisp was created in 1958"]
     [:p "one of the oldest languages still widely used"]
     [:p "Clojure is one of many \"dialects\" of Lisp"]]
    [:section
     [:h3 "\"hosted\" on the JVM"]
     [:p "Clojure data types are Java classes"]
     [:p "provides powerful interop with Java"]
     [:p "gives Clojure access to the entire Java ecosystem"]]]

   [:section
    [:section
     [:h2 "a Clojure demo"]]
    [:section
     [:p.fragment
      [:small "(you're looking at it)"]]]]

   [:section
    [:section (comic "javascript")]
    [:section [:h1 "+"]]
    [:section [:img.logo {:src "/img/clj-logo.png"}]]
    [:section [:h1 "="]]
    [:section [:img.logo {:src "/img/cljs-logo.png"}]]]

   [:section
    [:section
     [:h2 "ClojureScript"]
     [:blockquote
      "\"ClojureScript is a compiler for Clojure that targets JavaScript. It
      emits JavaScript code which is compatible with the advanced compilation
      mode of the Google Closure optimizing compiler.\""]]
    [:section
     [:h3 "does what you think"]
     [:p "compiles Clojure to JavaScript"]
     [:p "same language features you know and love"]
     [:p "powerful JavaScript interop"]]
    [:section
     [:h2 "Google Closure Compiler"]
     [:div [:small "(just to confuse you)"]]
     [:p "ClojureScript targets the Google Closure Compiler"]
     [:p "emits ES3 (IE6) compatible JavaScript"]
     [:p "dead code elimination out of the box"]]
    [:section
     [:h3 "Google Clojure Library"]
     [:p "entire library available to all ClojureScript programs out of the box"]
     [:p "used by Google Search, Gmail, Docs, Maps..."]
     [:p "provides UI widgets, DOM manipulation tools, cryptographic functions..."]]]

   [:section
    [:h2 "a whirlwind tour"]
    [:p ""]
    [:p ""]
    [:p ""]
    [:p ""]
    [:p ""]]

   [:section
    [:section
     [:h2 "data types"]]
    [:section
     [:h3 "primitive types"]
     [:p "come from the host language"]
     (code
       "user=> 5"
       "5"
       "user=> \"hey\""
       "\"hey\""
       "user=> 3.14159"
       "3.14159"
       "user=> true"
       "true"
       "user=> (type 5)"
       "java.lang.Long"
       )]
    [:section
     [:h3 "collections"]
     [:p "Clojure's own immutable types"]
     (code
       "; vector"
       "[1 2 3]"
       ""
       "; list"
       "(list \"yes\" [3 4])"
       ""
       "; map"
       "{:food-type \"pie\", :filling \"blueberry\"}"
       ""
       "; set"
       "#{1 2 3 5 7 11 13 17 23}")]]

   [:section
    [:section
     [:h2 "parenthesis"]]
    [:section
     [:h3 "every operation comes before the operands"]
     (code
       "user=> (println \"Hello!\") ; vs println(\"hello!\")"
       "Hello!"
       "nil"
       ""
       "; many symbols are not treated specially"
       "; foo, bar, woo!woo!, and >++> are all equally valid names"
       "user=> (+ 1 2) ; vs 1 + 2"
       "3"
       "user=> (* 3 4) ; vs 3 * 4"
       "12"
       "user=> (+ 2 (* 3 7)) ; vs 2 + 3 * 7"
       "20"
       "user=> (= 3 (+ 2 1))"
       "true")]
    [:section
     [:h3 "Java operator precedence table"]
     [:img {:src "/img/precedence-java.png"}]]
    [:section
     [:h3 "JavaScript operator precedence table"]
     [:img {:src "/img/precedence-js.png"}]]
    [:section
     [:h3 "Clojure operator precedence table"]
     [:p "left-to-right inside-out"]
     [:p.fragment "end of list."]]
    [:section
     [:h3 "everything is a list"]
     (code
       "(func-name arg1 arg2 arg3)      ; function calls"
       "(defn fooify [x] {:foobar x})   ; function definitions"
       "(-> 5 (* 3) (+ 7) (- 3))        ; macro expansion"
       "(if (= x 3) \"foo\")              ; special forms (core \"functions\")"
       "(list 1 2 3 4)                  ; lists"
       "[1 2 3 4 5]                     ; vectors"
       "{:yes \"please\" :no \"thank you\"} ; maps"
       "#{\"ok\" 1 true}                  ; sets"
       "(.toUpperCase \"look to me!\")    ; interop")]
    [:section
     [:h3 "everything is an expression"]
     (code
       "(if (= 15 (+ 5 10))"
       "  :yes"
       "  :no)"
       "; => :yes"
       ""
       "(case 2"
       "  1 :first"
       "  2 :second"
       "  3 :third)"
       "; => :second"
       )]
    [:section
     [:h3 "everything is a value"]
     [:p "what you see is what you get"]
     [:p "plain data structures are used for everything"]
     [:iframe {:width "560"
               :height "315"
               :data-src "https://www.youtube.com/embed/-6BsiVyC1kM"
               :frameborder "0"
               :allowfullscreen true}]]
    [:section
     [:h3 "everything is data"]
     [:p "\"code is data\""]
     [:p "the Clojure reader parses code into the same core data structures"]
     [:p "consequently, macros use them too"]]
    [:section
     [:h3 "everything is uniform"]
     [:p "everything fits into a simple mental model"]
     [:p "code can be manipulated with ease"]
     [:p "use the same core functions for everything"]]]

   [:section
    [:section [:h2 "immutability"]]
    [:section
     [:h3 "imagine a world"]
     (code
       "sadlang> x = 5"
       "sadlang> x"
       "5"
       "sadlang> x + 3"
       "8"
       "sadlang> x == 5"
       "false"
       "sadlang> x"
       "8")
     [:small.fragment "(now imagine doing threaded programming)"]]
    [:section
     [:h3 "we live in this world!"]
     (code
       "> nums = [3, 2, 1]"
       "[ 3, 2, 1 ]"
       "> nums.sort()"
       "[ 1, 2, 3 ]"
       "> nums === [3, 2, 1]"
       "false"
       "> nums"
       "[ 1, 2, 3 ]"
       "> nums === [1, 2, 3]"
       "false"
       "> [1, 2, 3] === [1, 2, 3]"
       "false")
     [:p "copied and pasted from a node repl session"]]
    [:section
     [:h3 "immutable and persistent"]
     (code
       "user=> (def nums [3 2 1])"
       "#'user/nums"
       "user=> (sort nums)"
       "(1 2 3)"
       "user=> (= nums [3 2 1])"
       "true"
       "user=> nums"
       "[3 2 1]"
       "user=> (= [1 2 3] [1 2 3])"
       "true")]
    [:section
      [:h3 "JavaScript vs Clojure"]
      (code
        "> x = {value: 42}"
        "{ value: 42 }"
        "> doStuff(x)"
        "undefined"
        "> x.value"
        "??? // literally no idea")
      (code
        "user=> (def x {:value 42})"
        "#'user/x"
        "user=> (do-stuff x)"
        "nil"
        "user=> (:value x)"
        "42 ; without a doubt")]]

   [:section
    [:section
     [:h2 "nil punning"]]
    [:section
     [:h3 "nil is not good"]
     [:p "source of a lot of errors"]
     [:p "Clojure embraces nil"]
     [:p "nil safely propagates through code"]]
    [:section
     [:h3 "lists"]
     (code
       "user=> (first [1 2 3])"
       "1"
       "user=> (last [1 2 3])"
       "3"
       "user=> (conj [1 2] 3)"
       "[1 2 3]"
       "user=> (first [])"
       "nil"
       "user=> (first nil)"
       "nil"
       "user=> (get 17 (first (second (first (last nil)))))"
       "nil"
       "user=> (conj nil 42)"
       "(42)")]
    [:section
     [:h3 "maps"]
     (code
       "user=> (def m {:value 37 :info {:dope true}})"
       "#'user/m"
       "user=> (:value m)"
       "37"
       "user=> (:dope (:info m))"
       "true"
       "user=> (:oops m)"
       "nil"
       "user=> (:i (:forgot (:my (:keys m))))"
       "nil"
       "user=> (assoc m :value 42)"
       "{:value 42, :info {:dope true}}"
       "user=> (assoc nil :value 42)"
       "{:value 42}")]
    [:section
     [:h3 "threading"]
     (code
       "; throws a NullPointerException"
       "(-> {}"
       "    (get-in [:nested :key])"
       "    (* 5))"
       ""
       "; fail fast when any form returns nil"
       "(some-> {}"
       "        (get-in [:nested :key])"
       "        (* 5))"
       )]
    [:section
     [:h3 "nil is an empty collection"]
     (code
       "user=> (empty? nil)"
       "true")]]

   [:section
    [:section
     [:h2 "concurrency"]]
    [:section
     [:h3 "atoms"]
     (code
       "user=> (def a (atom 0))"
       "#'user/a"
       "user=> (swap! a inc)"
       "1"
       "user=> (swap! a inc)"
       "2"
       "user=> (swap! a * 10)"
       "20"
       "user=> (swap! a dec)"
       "19"
       "user=> @a"
       "19")]
    [:section
     [:h3 "ACID without the D"]
     [:p "all " [:code "swap!"] " operations are atomic, consistent, and isolated"]
     [:p "the data inside is often completely immutable"]
     [:p "observable with " [:code "add-watch"]]]
    [:section
     [:h3 "other built in ref types"]
     [:p "vars"]
     [:p "refs"]
     [:p "futures"]
     [:p "agents"]]
    [:section
     [:h3 "core.async"]
     [:p "Go-inspired communicating sequential processes (CSP)"]
     [:p "not part of the core language, simply a library"]
     [:p "available in Clojure and ClojureScript"]]]

   [:section
    [:section
     [:h2 "simplicity"]]
    [:section
     [:blockquote
      "\"Encapsulation is folly... encapsulation is for hiding implementation
      details... data has no implementation.\""]
     [:p "&mdash; Rich Hickey"]]
    [:section
     [:blockquote
      "\"It is better to have 100 functions operate on one data structure than
      10 functions on 10 data structures.\""]
     [:p "&mdash; Alan Perlis"]]
    [:section
     [:h3 "use plain data structures for everything"]
     [:p "all data has the same interface"]
     [:p "everything is familiar"]]
    [:section
     [:h3 "hiccup/reagent"]
     (code
       "[:table"
       " [:thead"
       "  [:tr [:td {:colspan \"2\"}] \"Header\"]]"
       " [:tbody"
       "  [:tr"
       "   [:td \"column 1\"]"
       "   [:td \"column 2\"]]]]")]
    [:section
     [:h3 "ring"]
     (code
       "(defn handler [request]"
       "  {:status 200"
       "   :headers {\"Content-Type\" \"text/plain\"}"
       "   :body (str \"you visited \" (:uri request))})")]
    [:section
     [:h3 "com.stuartsierra.component"]
     [:blockquote "\"managed lifecycle of stateful objects in Clojure\""]
     [:p "Clojure's answer to OOP and DI systems like Spring Boot"]
     [:p "~350 lines of code, 1,289 stars, 0 issues, 0 pull requests"]]]

   [:section
    [:h2 "Clojure code is better code"]
    [:p "usually less code, because each piece has more meaning"]
    [:p "typically nameless patterns are given names that can re-used everywhere"]
    [:p "the REPL gives you extremely tight feedback loops"]]

   [:section
    [:h2 "honorable mentions"]
    [:p "clojure.spec"]
    [:p "macros"]
    [:p "http-kit"]]

   [:section
    [:h1 "thank you!"]]])

(def page
  [:html
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "Clojure: Embrace the Parenthesis"]
    (include-css "/reveal/css/reveal.css"
                 "/reveal/css/theme/moon.css"
                 "/reveal/lib/css/zenburn.css"
                 "/styles.css")]
   [:body
    [:div.reveal slides]
    (include-js "/reveal/lib/js/head.min.js"
                "/reveal/js/reveal.js"
                "/main.js")]])

(defroutes app-routes
  (GET "/" [] (html5 page)))

(def handler
  (-> app-routes
      (wrap-defaults site-defaults)))

; the repl
; figwheel

; clojurescript demo

; spec
; macros
