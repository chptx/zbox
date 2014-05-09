(ns leiningen.zbox.coffeec
  (:use clojure.java.io)
  (:require
   [me.raynes.fs :as fs]
   [dieter.pools :refer [make-pool]]
   [dieter.path :refer [find-asset]]
   [dieter.jsengine :refer [run-compiler]])
  (:gen-class))

(def pool (make-pool))

(defn compile-coffeescript [file]
    (run-compiler pool
                  ["coffee-script.js" "coffee-wrapper.js"]
                  "compileCoffeeScript"
                  file))

(defn input-list [dir]
  (filter #(and (not (.isDirectory %))
                (re-matches #".*[.]coffee$" (.getName %)))
          (file-seq (file dir))))

(defn remove-from-end [s end]
  (if (.endsWith s end)
    (.substring s 0 (- (count s)
                       (count end)))
    s))

(defn path-in-parent [fullp parentp]
  (clojure.string/join
   "/"
   (reduce (fn [a b]
             (if (= (first a) b)
               (next a)
               nil))
           (fs/split fullp)
           (fs/split parentp))))

(defn output [fname content]
  (let [p (.getPath (fs/parent fname))]
    (when-not (fs/exists? p)
      (fs/mkdirs p))
    (with-open [out (java.io.FileOutputStream. fname)]
      (.write out (.getBytes content)))))

(defn coffeec [in out]
  (println "coffeec " in out)
  (doseq [f (input-list in)]
    (output (str (remove-from-end out "/") "/"
                 (clojure.string/replace (path-in-parent (.getPath f) in) #".coffee$" ".js"))
            (compile-coffeescript f))))
