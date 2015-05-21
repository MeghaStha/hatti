(defproject org.clojars.onaio/hatti "0.1.0-SNAPSHOT"
  :description "A cljs dataview from your friends at Ona.io"
  :dependencies [;; CORE HATTI REQUIREMENTS
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3196"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [sablono "0.3.1"]
                 [org.omcljs/om "0.8.8"]
                 [inflections "0.9.7"]
                 ;; CLJX
                 [com.keminglabs/cljx "0.6.0" :exclusions [org.clojure/clojure]]
                 ;; FOR CHARTS
                 [com.keminglabs/c2 "0.2.4-SNAPSHOT"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [clj-time "0.7.0"]
                 [com.andrewmcveigh/cljs-time "0.2.3"]
                 ;; JS REQUIREMENTS
                 [cljsjs/moment "2.9.0-0"]
                 [prabhasp/leaflet-cljs "0.7.3-SNAPSHOT"]
                 [cljsjs/jquery "1.9.1-0"]
                 [prabhasp/slickgrid-cljs "0.0.1-SNAPSHOT"]
                 [prabhasp/osmtogeojson-cljs "2.2.5-SNAPSHOT"]
                 ;; CLIENT REQUIREMENTS
                 [prismatic/dommy "0.1.2"]
                 [cljs-http "0.1.17"]]
  :plugins [[lein-cljsbuild "1.0.5"]
            [com.keminglabs/cljx "0.6.0" :exclusions [org.clojure/clojure]]]
  :source-paths ["src/cljs"
                 "target/generated/src/clj"
                 "target/generated/src/cljs"
                 "target/classes"]
  :clean-targets ["out/hatti" "out/hatti.js"]
  :prep-tasks [["cljx" "once"] "javac" "compile"]
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/generated/src/clj"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/src/cljs"
                   :rules :cljs}]}
  :cljsbuild {
    :builds [{:id "test"
              :source-paths ["src/cljs"
                             "test/cljs"
                             "target/generated/src/cljs"]
              :notify-command ["phantomjs"
                               "phantom/unit-test.js"
                               "phantom/unit-test.html"
                               "target/main-test.js"]
              :compiler {:output-to "target/main-test.js"
                         :optimizations :whitespace
                         :pretty-print true}}
             {:id "hatti"
              :source-paths ["src/cljs"
                             "target/generated/src/cljs"]
              :compiler {
                :output-to "out/hatti.js"
                :output-dir "out"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]
    :test-commands {"unit-test"
                    ["phantomjs"
                     "phantom/unit-test.js"
                     "phantom/unit-test.html"
                     "target/main-test.js"]}})
