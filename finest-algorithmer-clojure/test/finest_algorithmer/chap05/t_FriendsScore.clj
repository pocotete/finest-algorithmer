(ns finest_algorithmer.chap05.t-FriendsScore
  (:use midje.sweet)
  (:use [finest_algorithmer.chap05.FriendsScore]))

;; ==================
;;  main-test
;; ==================
; test-data-01
(def friends01  ["NNN" "NNN" "NNN"])
(def returns01  0)

; test-data-02
(def friends02  ["NYY" "YNY" "YYN"])
(def returns02  2)

; test-data-03
(def friends03  ["NYNNN" "YNYNN" "NYNYN" "NNYNY" "NNNYN"])
(def returns03  4)

; test-data-04
(def friends04  ["NNNNNNNNNNNNNNY"
                 "NNNNNNNNNNNNNNN"
                 "NNNNNNNYNNNNNNN"
                 "NNNNNNNYNNNNNNY"
                 "NNNNNNNNNNNNNNY"
                 "NNNNNNNNYNNNNNN"
                 "NNNNNNNNNNNNNNN"
                 "NNYYNNNNNNNNNNN"
                 "NNNNNYNNNNNYNNN"
                 "NNNNNNNNNNNNNNY"
                 "NNNNNNNNNNNNNNN"
                 "NNNNNNNNYNNNNNN"
                 "NNNNNNNNNNNNNNN"
                 "NNNNNNNNNNNNNNN"
                 "YNNYYNNNNYNNNNN"])
(def returns04  6)

;; --------------------------------------------------------
;; highestScore
;  defn highestScore [friends]
;; --------------------------------------------------------
(facts "-- Main Test -- highestScore"
  (fact "01"
    (highestScore friends01) => returns01
  )
  (fact "02"
    (highestScore friends02) => returns02
  )
  (fact "03"
    (highestScore friends03) => returns03
  )
  (fact "04"
    (highestScore friends04) => returns04
  )
)

;; ==================
;;  function-test
;; ==================
; 省略

