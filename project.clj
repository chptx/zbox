(defproject org.clojars.chptx/zbox "0.0.2"
  :description "resource preprocessing toolbox"
  :url "http://github.com/chptx/zbox"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
             [clj-v8 "0.1.5"]
             [me.raynes/fs "1.4.3"]
             [org.clojure/tools.nrepl "0.2.3" :exclusions [[org.clojure/clojure]]]
             [clojure-complete "0.2.3" :exclusions [[org.clojure/clojure]]]
             [org.clojure/clojure "1.5.1"]
             [dieter "0.4.1"]
             [org.jruby/jruby-complete "1.7.10"]
             [org.clojars.chptx/compass "0.12.2"]]
  :eval-in-leiningen true)
