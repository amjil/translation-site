(ns kit.translation-site.web.pages.segmentation
  (:require
   [kit.translation-site.web.htmx :refer [ui page] :as htmx]))

(defn main [request]
  (page
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "Abiya AI + Segmentation"]
    [:link {:href "/css/main.css" :rel "stylesheet"}]
    [:link {:href "/img/abiya_logo_icon.png" :rel "icon" :type "image/x-icon"}]
    [:script {:src "https://unpkg.com/htmx.org@1.9.10" :defer true}]
    [:script {:src "https://unpkg.com/htmx.org/dist/ext/ws.js" :defer true}]
    [:script {:src "https://unpkg.com/hyperscript.org@0.9.12" :defer true}]
    [:script {:src "https://unpkg.com/alpinejs" :defer false}]]
   [:body {:class "h-screen w-screen"}
    [:nav {:class "bg-grey md:text-sm border-b h-20 shadow-md"}
     [:div {:class "h-full gap-x-14 items-center mx-auto px-6 lg:px-8"}
      [:div {:class "h-full flex items-center justify-between py-1 md:block"}
       [:a {:href "javascript:void(0)"}
        [:img {:src "/img/abiya_logo_picture.png"
               :class "h-full"
               :alt "Float UI logo"}]]]]]
    [:div {:class "mx-auto flex flex-col px-6 lg:px-8 bg-slate-100"}
     [:h3 {:class "py-4 text-2xl font-bold leading-9 tracking-tight text-gray-900"}
      [:span {:class "border rounded-lg p-2 bg-white"}
       "Text Segmentation"]]
     [:div {:id "response_result"
            :x-data "{ lang: 'en'}"}
      [:form {:hx-post "/api/segmentation"
              :hx-target "#response_result"
              :hx-ext "submitjson"}
       [:input {:type "hidden" :name "from" ":value" "lang"}]
       [:div {:class "flex py-4"}
        [:a {:href "javascript:void(0)"
             ":class" "lang == 'en' ? 'bg-gray-800' : 'bg-gray-400'"
             "@click" "lang = 'en'"
             :class "rounded-l-lg gap-x-2 py-2 px-4 text-white text-xs hover:bg-gray-700 active:bg-gray-900 md:inline-flex"}
         "English"]
        [:a {:href "javascript:void(0)"
             ":class" "lang == 'mo' ? 'bg-gray-800' : 'bg-gray-400'"
             "@click" "lang = 'mo'"
             :class "gap-x-2 py-2 px-4 text-white text-xs hover:bg-gray-700 active:bg-gray-900 md:inline-flex"}
         "Mongolian"]
        [:a {:href "javascript:void(0)"
             ":class" "lang == 'ch' ? 'bg-gray-800' : 'bg-gray-400'"
             "@click" "lang = 'ch'"
             :class "gap-x-2 py-2 px-4 text-white text-xs hover:bg-gray-700 active:bg-gray-900 md:inline-flex"}
         "Chinese"]
        [:a {:href "javascript:void(0)"
             ":class" "lang == 'ja' ? 'bg-gray-800' : 'bg-gray-400'"
             "@click" "lang = 'ja'"
             :class "gap-x-2 py-2 px-4 text-white text-xs hover:bg-gray-700 active:bg-gray-900 md:inline-flex"}
         "Japanese"]
        [:a {:href "javascript:void(0)"
             ":class" "lang == 'cy' ? 'bg-gray-800' : 'bg-gray-400'"
             "@click" "lang = 'cy'"
             :class "rounded-r-lg gap-x-2 py-2 px-4 text-white text-xs hover:bg-gray-700 active:bg-gray-900 md:inline-flex"}
         "Cryllic"]]
       [:div {:class "flex flex-col rounded-lg border bg-white"}
        [:div {:class "flex h-56 w-full px-4 border-b overflow-auto"}
         [:textarea {:placeholder "Type here"
                     :autocomplete "off"
                     :name "original_txt"
                     :class "w-full h-full resize-none border-none focus:outline-none outline-none border-transparent focus:border-transparent focus:ring-0"}]]
        [:div {:class "flex h-56 w-full px-4 overflow-auto"}]]
       [:div {:class "w-full px-4 py-8"}
      ;; "请输入正确的文本。本工具按照以下符号将文本切换成句子，有。！？；等"
        [:span "Please enter correct text. This tool switches text into sentences according to the following symbols, with. ! ? ; etc."]]
       [:div {:class "flex flex-col rounded-lg border p-4 bg-white"}
        [:span "Divided into 0 sentences in total"]]
       [:div {:class "flex justify-end py-4"}
        [:button {;:href "javascript:void(0)"
                  :type "submit"
                  :class "gap-x-1 py-2 px-4 text-white font-medium bg-gray-800 hover:bg-gray-700 active:bg-gray-900 rounded-full md:inline-flex"}
         "Segment"]]]]]]))