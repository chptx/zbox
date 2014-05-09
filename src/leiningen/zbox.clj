(ns leiningen.zbox
  (:import (org.jruby Main))
  (:require
   [leiningen.zbox.hbsc :as hb]
   [leiningen.zbox.coffeec :as cf]))

(defn zbox
  "resource processing."
  [project & args]
  (case (nth args 0)
    "hbsc" (hb/hbsc "src/hbs" "resources/public/js/templates.js")
    "compass" (org.jruby.Main/main (into-array (into ["-S" "compass"] (next args))))
    "coffeec" (cf/coffeec "src/coffee" "resources/public/js")))
