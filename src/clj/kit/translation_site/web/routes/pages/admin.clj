(ns kit.translation-site.web.routes.pages.admin
  (:require
   [kit.translation-site.web.htmx :refer [ui page] :as htmx]))

(defn main [request]
  (page
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "Abiya AI + Translation - Admin"]
    [:link {:href "/css/main.css" :rel "stylesheet"}]
    [:script {:src "https://unpkg.com/htmx.org@1.9.10" :defer true}]
    [:script {:src "https://unpkg.com/htmx.org/dist/ext/ws.js" :defer true}]
    [:script {:src "https://unpkg.com/hyperscript.org@0.9.12" :defer true}]
    [:script {:src "https://unpkg.com/alpinejs" :defer false}]]
   [:body
    [:nav {:class "bg-grey md:text-sm"}
     [:div {:class "gap-x-14 items-center max-w-screen-xl mx-auto px-4 md:flex md:px-8"}
      [:div {:class "flex items-center justify-between py-5 md:block"}
       [:a {:href "javascript:void(0)"}
        [:img {:src "https://www.floatui.com/logo.svg"
               :width 120
               :height 50
               :alt "Float UI logo"}]]]]]]))

