(set-env!
  :source-paths #{"src/cljs"}
  :resource-paths #{"resources"}

  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.9.225"]
                  [adzerk/boot-cljs "1.7.228-1"]
                  [pandeiro/boot-http "0.7.3"]
                  [adzerk/boot-reload "0.4.12"]
                  [adzerk/boot-cljs-repl "0.3.2"]
                  [com.cemerick/piggieback "0.2.1"]     ;; needed by bREPL
                  [weasel "0.7.0"]                      ;; needed by bREPL
                  [org.clojure/tools.nrepl "0.2.12"]])  ;; needed by bREPL


(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]] ;; make serve task visible
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])

;; define dev task as composition of subtasks
(deftask dev
         "Launch Immediate Feedback Development Environment"
         []
         (comp
           (serve :dir "target")
           (watch)
           (reload)
           (cljs-repl)
           (cljs)
           (target :dir #{"target"})))