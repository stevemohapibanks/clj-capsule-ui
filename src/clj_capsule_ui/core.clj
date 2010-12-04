(ns clj-capsule-ui.core
	(:use compojure.core
	      ring.adapter.jetty
	      clj-capsule.core
	      clj-capsule-ui.routes))

(set-active-session "digital-science" "06f72861a7c10ace4a30f8a0755cd8aa")
(future (run-jetty (var ui-routes) {:port 8080}))