(ns finest_algorithmer.chap05.t_ThePalindrome
  (:use midje.sweet)
  (:use [finest_algorithmer.chap05.ThePalindrome]))

;; ==================
;;  main-test
;; ==================

;; test-data-01
(def s01 "abab")
(def ret01 5)

(def s02 "abacaba")
(def ret02 7)

(def s03 "qwerty")
(def ret03 11)

(def s04 "abdfhdyrbdbsdfghjkllkjhgfds")
(def ret04 38)

;; --------------------------------------------------------
;; find
;  defn find [s]
;; --------------------------------------------------------
(facts "-- Main Test -- find"
  (fact "01"
    (find s01) => ret01
  )
  (fact "02"
    (find s02) => ret02
  )
  (fact "03"
    (find s03) => ret03
  )
  (fact "04"
    (find s04) => ret04
  )
)

;; ==================
;;  function-test
;; ==================
;; ------------------------------------------
;;  examine
;;  defn examine [s]
;; ------------------------------------------
(facts "examine 01"
  (fact "01"
    (examine "abcba") => true
  )
  (fact "02"
    (examine "apple") => false
  )
)

;; ------------------------------------------
;;  makeYamamotoyama
;;  defn makeYamamotoyama [s]
;; ------------------------------------------
(facts "makeYamamotoyama 01"
  (fact "01"
    (makeYamamotoyama "abab") => "ababa"
  )
  (fact "02"
    (makeYamamotoyama "abcba") => "abcba"
  )
  (fact "03"
    (makeYamamotoyama "apple") => "applelppa"
  )
)