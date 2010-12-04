(ns clj-capsule-ui.controllers
  (:use clojure.contrib.json
        clj-capsule-ui.views)
  (:require [clj-capsule.party :as party]))

(def fake-search
  [{:organisationName "Pfizer", :organisationId "4819339", :lastName "Williams-Jones", :firstName "Bryn",
    :pictureURL "https://assets0.capsulecrm.com/theme/default/images/person_avatar_70.png?543029", 
    :contacts {:email {:id "7682570", :emailAddress "bryn.i.williams-jones@pfizer.com"}}, :id "4268486", :type "person"}
   {:organisationName "Nike", :organisationId "4905985", :lastName "Jones", :firstName "Charlie S", 
    :pictureURL "https://assets0.capsulecrm.com/theme/default/images/person_avatar_70.png?543029", 
    :about "part of the CC open patent project, GreenXchange." , 
    :contacts {:email {:id "7681770", :emailAddress "Charlie.Jones2@nike.com"}}, :id "4267705", :type "person"} 
   {:lastName "Jones", :firstName "Ryan", :pictureURL "https://assets0.capsulecrm.com/theme/default/images/person_avatar_70.png?543029", 
    :contacts {:email {:id "7682317", :emailAddress "rjones@pubget.com"}}, :id "4268239", :type "person"}
   {:name "Google", :pictureURL "https://assets0.capsulecrm.com/theme/default/images/person_avatar_70.png?543029", 
    :contacts {:email {:id "7682317", :emailAddress "rjones@pubget.com"}}, :id "3460386", :type "organisation"}])
  
(defn search
  ([query] (search query :html))
  ([query format]
    (let [parties (party/search query)]
    ; (let [parties fake-search]
      (cond
        (= format :html)    (render-results parties)
        (= format :json)    (json-str parties)))))
  

