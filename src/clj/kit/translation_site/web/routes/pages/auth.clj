(ns kit.translation-site.web.routes.pages.auth
  (:require
   [kit.translation-site.web.htmx :refer [ui page] :as htmx]))

(defn login [request]
  (page
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "Abiya AI + Translation - Login"]
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
               :alt "Float UI logo"}]]]]]
    [:div {:class "flex min-h-full flex-col justify-center px-6 py-12 lg:px-8"}
     [:h2 {:class "mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900"}
      "Sign in to your account"]]
    [:div {:class "mt-10 sm:mx-auto sm:w-full sm:max-w-sm"}
     [:form {:class "space-y-6"}
      [:div
       [:label {:class "block text-sm font-medium leading-6 text-gray-900"
                :for "email"}
        "Email address"]
       [:div.mt-2
        [:input {:id "email"
                 :name "email"
                 :type "email"
                 :autocomplete "email"
                 :required true
                 :class "block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"}]]]
      [:div
       [:div {:class "flex items-center justify-between"}
        [:label {:class "block text-sm font-medium leading-6 text-gray-900"
                 :for "password"}
         "Password"]
        [:div.text-sm
         [:a {:href "#"
              :class "font-semibold text-indigo-600 hover:text-indigo-500"}
          "Forgot password?"]]]

       [:div.mt-2
        [:input {:id "password"
                 :name "password"
                 :type "password"
                 :autocomplete "current-password"
                 :required true
                 :class "block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"}]]]
      [:div
       [:button {:type "submit"
                 :class "flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"}
        "Sign in"]]]]]))