(ns clj-capsule-ui.routes
	(:use compojure.core
	      ring.adapter.jetty
	      hiccup.core
	      clj-capsule.core
	      clj-capsule-ui.views
	      clj-capsule-ui.controllers)
	(:require [compojure.route :as route]))

(defroutes ui-routes
	(GET "/" []
		(render-home))
	(GET ["/find/:query"] [query]
	  (search query :json))
	(route/files "/")
  (route/not-found "<h1>Page not found</h1>"))
