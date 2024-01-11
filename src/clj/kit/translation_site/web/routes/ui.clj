(ns kit.translation-site.web.routes.ui
  (:require
   [kit.translation-site.web.middleware.exception :as exception]
   [kit.translation-site.web.middleware.formats :as formats]
   [kit.translation-site.web.routes.utils :as utils]
   [kit.translation-site.web.htmx :refer [ui page] :as htmx]
   [integrant.core :as ig]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]))

(def languages ["Mongolian" "English" "Chinese" "Russian" "Japanese"])

(defn lang-selector-button [idx]
  [:div {:class "relative py-4"}
   [:button {:type "button"
             :class "relative w-full cursor-default rounded-full border-none bg-zinc-100 py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm focus:outline-none sm:text-sm sm:leading-6"
             :aria-haspopup "listbox" :aria-expanded "true" :aria-labelledby "listbox-label"
             "x-on:click"
             (str
              (if (zero? idx)
                "source_open = ! source_open; target_open = false"
                "target_open = ! target_open; source_open = false"))}
    [:span {:class "flex items-center"}
     [:span {:class "ml-3 block truncate"
             :x-text (if (zero? idx)
                       "source_value"
                       "target_value")}]]
    [:span {:class "pointer-events-none absolute inset-y-0 right-0 ml-3 flex items-center pr-2"}
     [:svg {:class "h-5 w-5 text-gray-400"
            :viewBox "0 0 20 20"
            :fill "currentColor"
            :aria-hidden "true"}
      [:path {:fill-rule "evenodd"
              :d "M10 3a.75.75 0 01.55.24l3.25 3.5a.75.75 0 11-1.1 1.02L10 4.852 7.3 7.76a.75.75 0 01-1.1-1.02l3.25-3.5A.75.75 0 0110 3zm-3.76 9.2a.75.75 0 011.06.04l2.7 2.908 2.7-2.908a.75.75 0 111.1 1.02l-3.25 3.5a.75.75 0 01-1.1 0l-3.25-3.5a.75.75 0 01.04-1.06z"
              :clip-rule "evenodd"}]]]]
   [:div {:x-show (if (zero? idx)
                    "source_open"
                    "target_open")}
    [:ul {:class "absolute z-10 mt-1 max-h-56 overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm "
          :tabindex "-1" :role "listbox" :aria-labelledby "listbox-label" :aria-activedescendant "listbox-option-0"}
     (map
      (fn [itm]
        [:li {:class "text-gray-900 relative cursor-default select-none py-2 pl-3 pr-9"
              :role "option"
              "x-on:click" (if (zero? idx)
                             (str
                              "if (target_value == '" itm "') target_value = source_value;"
                              "source_value = '" itm "';source_open = false")
                             (str
                              "if (source_value == '" itm "') source_value = target_value;"
                              "target_value = '" itm "';target_open = false"))}
         [:div {:class "flex items-center"}
          [:span {:class "font-normal ml-3 block truncate"}
           itm]]
         [:div {:x-show (if (zero? idx)
                          (str "source_value == '" itm "'")
                          (str "target_value == '" itm "'"))}
         [:span {:class "text-indigo-600 absolute inset-y-0 right-0 flex items-center pr-4"}
          [:svg {:class "h-5 w-5"
                 :viewBox "0 0 20 20"
                 :fill "currentColor"
                 :aria-hidden "true"}
           [:path {:fill-rule "evenodd"
                   :d "M16.704 4.153a.75.75 0 01.143 1.052l-8 10.5a.75.75 0 01-1.127.075l-4.5-4.5a.75.75 0 011.06-1.06l3.894 3.893 7.48-9.817a.75.75 0 011.05-.143z"
                   :clip-rule "evenodd"}]]]]])
      languages)]]])

(defn home [request]
  (page
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "AI + Translation"]
    [:link {:href "/css/main.css" :rel "stylesheet"}]
    [:script {:src "https://unpkg.com/htmx.org@1.9.10" :defer true}]
    [:script {:src "https://unpkg.com/htmx.org/dist/ext/ws.js" :defer true}]
    [:script {:src "https://unpkg.com/hyperscript.org@0.9.12" :defer true}]
    [:script {:src "https://unpkg.com/alpinejs" :defer true}]
    [:script {:type "text/hyperscript"}
     ""]]
   [:body
    [:nav {:class "bg-white md:text-sm border-b"}
     [:div {:class "gap-x-14 items-center max-w-screen-xl mx-auto px-4 md:flex md:px-8"}
      [:div {:class "flex items-center justify-between py-5 md:block"}
       [:a {:href "javascript:void(0)"}
        [:img {:src "https://www.floatui.com/logo.svg"
               :width 120
               :height 50
               :alt "Float UI logo"}]]]
      [:div {:class "flex-1 items-center mt-8 md:mt-0 md:flex"}
       [:ul {:class "justify-center items-center space-y-6 md:flex md:space-x-6 md:space-y-0"}
        (map-indexed
         (fn [idx itm]
           [:li {:key idx
                 :class (str "py-1" (if (zero? idx)
                                      "border-b-2 border-indigo-600"
                                      ""))}
            [:a {:href "javascript:void(0)"
                 :class "block py-2 px-3 rounded-lg text-gray-700 hover:text-gray-900 hover:bg-gray-100 duration-150"}
             itm]])
         ["Overview" "Integration" "Billing" "Plans"])]]
      [:div {:class "flex-1 gap-x-6 items-center justify-end mt-6 space-y-6 md:flex md:space-y-0 md:mt-0"}
       [:a {:href "javascript:void(0)"
            :class "block text-gray-700 hover:text-gray-900"}
        "Log in"]
       [:a {:href "javascript:void(0)"
            :class "flex items-center justify-center gap-x-1 py-2 px-4 text-white font-medium bg-gray-800 hover:bg-gray-700 active:bg-gray-900 rounded-full md:inline-flex"}
        "Sign in"]]]]
    [:div {:class "h-10"}]
    [:div {:class "flex flex-col max-w-4xl mx-auto rounded-[18px] border"
           :x-data "{ source_value: 'English', target_value: 'Mongolian'}"}
     [:div {:class "border-b px-4"}
      [:div {:class "w-full max-w-fit flex"
             :x-data "{ source_open: false, target_open: false }"}
       (lang-selector-button 0)
       [:div {:class "self-center mx-4"}
        [:svg {:xmlns "http://www.w3.org/2000/svg" 
               :fill "none" 
               :viewBox "0 0 24 24" :stroke-width "1.5" :stroke "currentColor" :class "w-6 h-6"}
         [:path {:stroke-linecap "round" :stroke-linejoin "round" :d "M7.5 21 3 16.5m0 0L7.5 12M3 16.5h13.5m0-13.5L21 7.5m0 0L16.5 12M21 7.5H7.5"}]]]
       (lang-selector-button 1)]]
     [:div {:class "flex h-60 w-full px-4"}
      [:div {:class "border-r w-1/2"}
       [:textarea {:placeholder "Type here"
                   :autocomplete "off"
                   :class "resize-none border-none focus:outline-none outline-none border-transparent focus:border-transparent focus:ring-0"}]]
      [:div {:class "w-1/2"}
       [:div {:class "resize-none border-none outline-none focus:outline-none outline-none border-transparent focus:border-transparent focus:ring-0"}]]]]]))

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
