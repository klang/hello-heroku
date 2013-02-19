(ns hello-heroku.mem)

(def ^:dynamic *drop* (ref {}))

(defn mset [key value]
  (dosync (alter *drop* assoc key value) nil))
(defn mget [key]
  (dosync
   (let [return (@*drop* key)]
     (alter *drop* dissoc key)
     return)))