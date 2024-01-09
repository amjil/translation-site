(ns kit.translation-site.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[translation-site starting]=-"))
   :start      (fn []
                 (log/info "\n-=[translation-site started successfully]=-"))
   :stop       (fn []
                 (log/info "\n-=[translation-site has shut down successfully]=-"))
   :middleware (fn [handler _] handler)
   :opts       {:profile :prod}})
