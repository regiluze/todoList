(ns todo.core
    (:require
          [dommy.core :as dommy :refer-macros [sel1]]))


(enable-console-print!)

(println "Hello world!")

(def taskName (sel1 :#task))
(def priority (sel1 :#priority))
(def tasks (sel1 :#tasks))

(defn deleteTask [evt]
  (let [atxt (-> evt (.-currentTarget) dommy/value) ]
     (.log js/console atxt))
  )

(defn taskIndex! []
  (let [tasks (dommy/sel1 "#tasks") ]
     (.-childElementCount tasks)
  ))

(defn textElement [text]
  (let [el (dommy/create-element :span)]
          (dommy/set-text! el text)))

(defn buttonElement [name]
  (let [el (dommy/create-element :button)]
          (dommy/set-text! el "Delete")
          (dommy/set-value! el (taskIndex!))
          (dommy/listen! el :click deleteTask)))

(defn create [name priority]
    (let [el (dommy/create-element :li)
           delButton (dommy/create-element :button) ]
                  (dommy/set-text! delButton "Delete")
                  (dommy/append! el (textElement name))
                  (dommy/append! el (textElement " -- "))
                  (dommy/append! el (textElement priority))
                  (dommy/append! el (textElement " |  "))
                  (dommy/append! el (buttonElement name))))

(defn click-handler [e]
   (let [ task (create taskName.value priority.value) ]
         (dommy/append! tasks task)))

(dommy/listen! (sel1 :#add) :click click-handler)
