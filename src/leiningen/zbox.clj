(ns leiningen.zbox
  (:require
   [leiningen.zbox.hbsc :as hb]
   [leiningen.zbox.coffeec :as cf]))

(defn zbox
  [project & args]
  (case (nth args 0)
    "hbsc" (hb/hbsc "src/hbs" "resources/public/js/templates.js")
    "coffeec" (cf/coffeec "src/coffee" "resources/public/js")))
