(ns kit.translation-site.web.routes.ui
  (:require
   [kit.translation-site.web.middleware.exception :as exception]
   [kit.translation-site.web.middleware.formats :as formats]
   [kit.translation-site.web.routes.utils :as utils]
   [kit.translation-site.web.htmx :refer [ui page] :as htmx]
   [integrant.core :as ig]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]))

(defn home [request]
  (page
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "AI + Translation"]
    [:link {:href "/css/main.css" :rel "stylesheet"}]
    [:script {:src "https://unpkg.com/htmx.org@1.9.10" :defer true}]
    [:script {:src "https://unpkg.com/htmx.org/dist/ext/ws.js" :defer true}]
    [:script {:src "https://unpkg.com/hyperscript.org@0.9.12" :defer true}]]
   [:body
    [:header {:class "text-base lg:text-sm"}
     [:div {:class "bg-white items-center gap-x-14 px-4 max-w-screen-xl mx-auto lg:flex lg:px-8 lg:static"}
      [:div {:class "flex items-center justify-between py-3 lg:py-5 lg:block"}
       [:a {:href "javascript:void(0)"}
        [:img {:src "https://www.floatui.com/logo.svg"
               :width 120
               :height 50
               :alt "Float UI logo"}]]]
      ]
     [:nav.border-b
      [:ul {:class "flex items-center gap-x-3 max-w-screen-xl mx-auto px-4 overflow-x-auto lg:px-8"}
       (map-indexed
        (fn [idx itm]
          [:li {:key idx
                :class (str "py-1" (if (zero? idx)
                                     "border-b-2 border-indigo-600"
                                     ""))}
           [:a {:href "javascript:void(0)"
                :class "block py-2 px-3 rounded-lg text-gray-700 hover:text-gray-900 hover:bg-gray-100 duration-150"}
            itm]])
        ["Overview" "Integration" "Billing" "Plans"])]]]
    [:h1 {:class "text-3xl font-bold underline"}
     "Welcome to Htmx + Kit module"]
    [:button {:hx-post "/clicked" :hx-swap "outerHTML"} "Click me!"]]))

(defn clicked [request]
  (ui
   [:div "Congratulations! You just clicked the button!"]))

;; Routes
(defn ui-routes [_opts]
  [["/" {:get home}]
   ["/clicked" {:post clicked}]])

(def route-data
  {:muuntaja   formats/instance
   :middleware
   [;; Default middleware for ui
    ;; query-params & form-params
    parameters/parameters-middleware
    ;; encoding response body
    muuntaja/format-response-middleware
    ;; exception handling
    exception/wrap-exception]})

(derive :reitit.routes/ui :reitit/routes)

(defmethod ig/init-key :reitit.routes/ui
  [_ {:keys [base-path]
      :or   {base-path ""}
      :as   opts}]
  [base-path route-data (ui-routes opts)])
