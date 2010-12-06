(ns clj-capsule-ui.views
	(:use hiccup.core hiccup.page-helpers hiccup.form-helpers))

(def search-form
  (form-to {:id "query-form"} [:post "/find"]
    (text-field {:id "query"} "query")
    (submit-button {:id "find"} "Find")
    (submit-button {:id "clear"} "Clear")))

(def update-form
  (form-to {:id "update-form"} [:put "/update"]
    (label "tag" "Tag all with... ")
    (text-field {:id "tag"} "tag")
    (submit-button {:id "update"} "Update")))

(defmacro with-layout [& body]
  `(html5 
    [:head
      (include-js "/js/jquery-1.4.4.min.js"
                  "/js/jquery-ui-1.8.6.custom.min.js"
                  "/js/main.js")
      (include-css "/css/grid.css"
                   "/css/styles.css")]
    [:body
      ~@body]))        

(defn render-home []
  (with-layout
    [:div {:class "row"}
      [:h1 {:class "main"} "Capsule CRM UI"]
      [:div {:id "container"}
      
        [:div {:class "row"}
          [:div {:class "column grid_16"} search-form]]
        
        [:div {:class "row"}
          [:div {:class "column grid_8 search"}
            [:h2 "Drag to edit"]
            [:ul {:id "available"}]]
          [:div {:class "column grid_8 update"}
            [:h2 "Ready to edit"]
            [:ul {:id "selected"}]
            update-form]]
            
        [:div {:class "clear"}]]]))
