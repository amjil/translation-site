(ns kit.translation-site.web.controllers.segmentation
  (:require
   [clj-http.client :as http]
   [cheshire.core :as cheshire]
   [clojure.string :as str]
   [ring.util.http-response :as http-response]
   [kit.translation-site.web.htmx :refer [ui page] :as htmx]))

(defn request
  [req]
  (let [params (select-keys (:params req) [:from :original_txt])
        response (http/post "http://100.64.0.2:8000/text_segmentation_service/en"
                            {:form-params params
                             :content-type :json})
        {{error :error_msg
          text :segmented_text
          num :sentence_number} :data
         status :status}
        (-> (:body response)
            (cheshire/parse-string true))
        text (if (not-empty text)
               (str/replace text #"\n" "<br/>")
               "")]

    (page
     [:form {:hx-post "/api/segmentation"
             :hx-target "#response_result"
             :x-data (str "{ lang: '" (:from params) "'}")}
      [:input {:type "hidden" :name "from" ":value" "lang"
               :value (:from params)}]
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
                    :class "w-full h-full resize-none border-none focus:outline-none outline-none border-transparent focus:border-transparent focus:ring-0"}
         (:original_txt params)]]
       [:div {:class "flex h-56 w-full px-4 overflow-auto"}
        (if (not-empty text) text "")]]
      [:div {:class "w-full px-4 py-8"}
       [:span {}
        "Please enter correct text. This tool switches text into sentences according to the following symbols, with. ! ? ; etc."]]
      [:div {:class "flex flex-col rounded-lg border p-4 bg-white"}
       [:span (str "Divided into " (if (zero? status) num 0) " sentences in total")]
       [:span {} (if (zero? status)
                   ""
                   error)]]
      [:div {:class "flex justify-end py-4"}
       [:button {;:href "javascript:void(0)"
                 :type "submit"
                 :class "gap-x-1 py-2 px-4 text-white font-medium bg-gray-800 hover:bg-gray-700 active:bg-gray-900 rounded-full md:inline-flex"}
        "Segment"]]])))