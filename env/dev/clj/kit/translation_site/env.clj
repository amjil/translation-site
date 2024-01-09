(ns kit.translation-site.env
  (:require
    [clojure.tools.logging :as log]
    [kit.translation-site.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[translation-site starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[translation-site started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[translation-site has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})
