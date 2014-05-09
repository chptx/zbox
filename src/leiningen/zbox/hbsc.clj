(ns leiningen.zbox.hbsc
  (:use clojure.java.io)
  (:require
   [me.raynes.fs :as fs]
   [dieter.pools :refer [make-pool]]
   [dieter.path :refer [find-asset]]
   [dieter.jsengine :refer [run-compiler]])
  (:gen-class))

(def pool (make-pool))

(defn preprocess-handlebars [file]
  (run-compiler pool
                ["ember-wrapper.js" "handlebars-1.1.2.js" "ember-template-compiler.js"]
                "compileEmberHandlebarsTemplate"
                file))

(defn input-list [dir]
  (filter #(and (not (.isDirectory %))
                (re-matches #".*[.]hbs$" (.getName %))) 
          (file-seq (file dir))))

(defn preprocess-all [dir]
  (let [builder (StringBuilder.)]
    (doseq [f (input-list dir)]
      (.append builder (preprocess-handlebars f))) 
    builder))

(defn output [fname sb]
  (let [p (.getPath (fs/parent fname))]
    (when-not (fs/exists? p)
      (fs/mkdirs p)))
  (with-open [out (java.io.FileOutputStream. fname)]
    (.write out (.getBytes (.toString sb)))))

(defn hbsc [indir outf]
  (println "hbsc " indir outf)
  (output outf (preprocess-all indir)))
