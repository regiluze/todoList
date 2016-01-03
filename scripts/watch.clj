(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'todo.core
   :output-to "out/todo.js"
   :output-dir "out"})
