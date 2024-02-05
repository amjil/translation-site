(ns kit.translation-site.web.pages.admin
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
   [:body {:class "h-screen w-screen"}
    [:nav {:class "bg-grey md:text-sm border-b"}
     [:div {:class "gap-x-14 items-center max-w-screen-xl mx-auto px-4 md:flex md:px-8"}
      [:div {:class "flex items-center justify-between py-1 md:block"}
       [:a {:href "javascript:void(0)"}
        [:img {:src "https://www.floatui.com/logo.svg"
               :width 120
               :height 50
               :alt "Float UI logo"}]]]]]
    [:div {:class "flex"}
     [:aside {:class "h-full"}
      [:div {:class  "bg-slate-800 font-sans"}
       [:div {:class "flex flex-col sm:flex-row sm:justify-around"}
        [:div {:class "w-64 h-screen"}
         [:nav
          [:div {:x-data "{ open: false }"}
           [:button {"@click" "open = !open"
                     :class "w-full flex justify-between items-center py-3 px-6 text-slate-100 cursor-pointer hover:bg-slate-600 hover:text-blue-500 focus:outline-none"}
            [:span {:class "flex items-center"}
             [:svg {:class "h-5 w-5", :viewbox "0 0 24 24", :fill "none", :xmlns "http://www.w3.org/2000/svg"}
              [:path {:d "M19 11H5M19 11C20.1046 11 21 11.8954 21 13V19C21 20.1046 20.1046 21 19 21H5C3.89543 21 3 20.1046 3 19V13C3 11.8954 3.89543 11 5 11M19 11V9C19 7.89543 18.1046 7 17 7M5 11V9C5 7.89543 5.89543 7 7 7M7 7V5C7 3.89543 7.89543 3 9 3H15C16.1046 3 17 3.89543 17 5V7M7 7H17",
                      :stroke "currentColor",
                      :stroke-width "2",
                      :stroke-linecap "round",
                      :stroke-linejoin "round"}]]
             [:span {:class "mx-4 font-medium"}
              "Dashboard"]]]]
          [:div {:x-data "{ open: false }"}
           [:button {"@click" "open = !open"
                     :class "w-full flex justify-between items-center py-3 px-6 text-slate-100 cursor-pointer hover:bg-slate-600 hover:text-blue-500 focus:outline-none"}
            [:span {:class "flex items-center"}
             [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewbox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :class "w-6 h-6"}
              [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"}]]
             [:span {:class "mx-4 font-medium"}
              "Personal Center"]]]]
          [:div {:x-data "{ open: false }"}
           [:button {"@click" "open = !open"
                     :class "w-full flex justify-between items-center py-3 px-6 text-slate-100 cursor-pointer hover:bg-slate-600 hover:text-blue-500 focus:outline-none"}
            [:span {:class "flex items-center"}
             [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewbox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :class "w-6 h-6"}
              [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "M11.35 3.836c-.065.21-.1.433-.1.664 0 .414.336.75.75.75h4.5a.75.75 0 0 0 .75-.75 2.25 2.25 0 0 0-.1-.664m-5.8 0A2.251 2.251 0 0 1 13.5 2.25H15c1.012 0 1.867.668 2.15 1.586m-5.8 0c-.376.023-.75.05-1.124.08C9.095 4.01 8.25 4.973 8.25 6.108V8.25m8.9-4.414c.376.023.75.05 1.124.08 1.131.094 1.976 1.057 1.976 2.192V16.5A2.25 2.25 0 0 1 18 18.75h-2.25m-7.5-10.5H4.875c-.621 0-1.125.504-1.125 1.125v11.25c0 .621.504 1.125 1.125 1.125h9.75c.621 0 1.125-.504 1.125-1.125V18.75m-7.5-10.5h6.375c.621 0 1.125.504 1.125 1.125v9.375m-8.25-3 1.5 1.5 3-3.75"}]]
             [:span {:class "mx-4 font-medium"}
              "Orders"]]]]
          [:div {:x-data "{ open: false }"}
           [:button {"@click" "open = !open"
                     :class "w-full flex justify-between items-center py-3 px-6 text-slate-100 cursor-pointer hover:bg-slate-600 hover:text-blue-500 focus:outline-none"}
            [:span {:class "flex items-center"}
             [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewbox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :class "w-6 h-6"}
              [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "m7.875 14.25 1.214 1.942a2.25 2.25 0 0 0 1.908 1.058h2.006c.776 0 1.497-.4 1.908-1.058l1.214-1.942M2.41 9h4.636a2.25 2.25 0 0 1 1.872 1.002l.164.246a2.25 2.25 0 0 0 1.872 1.002h2.092a2.25 2.25 0 0 0 1.872-1.002l.164-.246A2.25 2.25 0 0 1 16.954 9h4.636M2.41 9a2.25 2.25 0 0 0-.16.832V12a2.25 2.25 0 0 0 2.25 2.25h15A2.25 2.25 0 0 0 21.75 12V9.832c0-.287-.055-.57-.16-.832M2.41 9a2.25 2.25 0 0 1 .382-.632l3.285-3.832a2.25 2.25 0 0 1 1.708-.786h8.43c.657 0 1.281.287 1.709.786l3.284 3.832c.163.19.291.404.382.632M4.5 20.25h15A2.25 2.25 0 0 0 21.75 18v-2.625c0-.621-.504-1.125-1.125-1.125H3.375c-.621 0-1.125.504-1.125 1.125V18a2.25 2.25 0 0 0 2.25 2.25Z"}]]
             [:span {:class "mx-4 font-medium"}
              "My Services"]]
            [:span
             [:svg {:class "h-4 w-4", :viewbox "0 0 24 24", :fill "none", :xmlns "http://www.w3.org/2000/svg"}
              [:path {:x-show "! open", :d "M9 5L16 12L9 19", :stroke "currentColor", :stroke-width "2", :stroke-linecap "round", :stroke-linejoin "round", :style "display: none;"}]
              [:path {:x-show "open", :d "M19 9L12 16L5 9", :stroke "currentColor", :stroke-width "2", :stroke-linecap "round", :stroke-linejoin "round"}]]]]
           [:div {:x-show "open", :class "bg-slate-700"}
            [:a {:class "py-4 px-16 block text-sm text-slate-100 hover:bg-slate-600 hover:text-blue-500",
                 :href "#"} "Manage Accounts"]
            [:a {:class "py-4 px-16 block text-sm text-slate-100 hover:bg-slate-600 hover:text-blue-500",
                 :href "#"} "Manage Tickets"]]]]]]]]
     [:div
      [:p "content"]]]]))
