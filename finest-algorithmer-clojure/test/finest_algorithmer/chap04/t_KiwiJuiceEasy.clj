(ns finest_algorithmer.chap04.t-KiwiJuiceEasy
  (:use midje.sweet)
  (:use [finest_algorithmer.chap04.KiwiJuiceEasy]))

;; ==================
;;  main-test
;; ==================

;; test-data-01
(def capacities01 [20 20])
(def bottles01 [5 8])
(def fromId01 [0])
(def toId01 [1])
(def returns01 [0 13])

;; test-data-02
(def capacities02 [30,20,10])
(def bottles02 [10,5,5])
(def fromId02 [0,1,2])
(def toId02 [1,2,0])
(def returns02 [10,10,0])

;; test-data-03
(def capacities03 [14,35,86,58,25,62])
(def bottles03 [6,34,27,38,9,60])
(def fromId03 [1,2,4,5,3,3,1,0])
(def toId03 [0,1,2,4,2,5,3,1])
(def returns03 [0,14,65,35,25,35])

;; test-data-04
(def capacities04 [700000,800000,900000,1000000])
(def bottles04 [478478,478478,478478,478478])
(def fromId04 [2,3,2,0,1])
(def toId04 [0,1,1,3,2])
(def returns04 [0,156956,900000,856956])

;; --------------------------------------------------------
;; thePouring
;  defn thePouring [capacities bottles fromIds toIds]
;; --------------------------------------------------------
(facts "-- Main Test -- thePouring"
  (fact "01"
    (thePouring capacities01 bottles01 fromId01 toId01) => returns01
  )
  (fact "02"
    (thePouring capacities02 bottles02 fromId02 toId02) => returns02
  )
  (fact "03"
    (thePouring capacities03 bottles03 fromId03 toId03) => returns03
  )
  (fact "04"
    (thePouring capacities04 bottles04 fromId04 toId04) => returns04
  )
)

;; ==================
;;  function-test
;; ==================
(def t_bottle001 {:id 0 :content 5  :capa 10})
(def t_bottle002 {:id 1 :content 18 :capa 20})
(def t_bottle003 {:id 2 :content 3  :capa 30})
(def t_bottle004 {:id 3 :content 35 :capa 40})
(def t_bottle005 {:id 4 :content 0  :capa 5})

(def t_bottle001_1-2 {:id 0 :content 3  :capa 10})
(def t_bottle002_1-2 {:id 1 :content 20 :capa 20})

(def t_bottle001_1-3 {:id 0 :content 0  :capa 10})
(def t_bottle003_1-3 {:id 2 :content 8  :capa 30})

(def t_bottle001_1-4 {:id 0 :content 0  :capa 10})
(def t_bottle004_1-4 {:id 3 :content 40 :capa 40})

(def t_bottle005_5-1 {:id 0 :content 0  :capa 5})
(def t_bottle001_5-1 {:id 4 :content 5 :capa 10})

(def t_bottles1-2 [t_bottle001 t_bottle002])
(def t_bottles1-4 [t_bottle001 t_bottle004])

(def t_bottles1_2_3_4 [t_bottle001 t_bottle002 t_bottle003 t_bottle004])

(def t_fromTo_1-2 {:from 0 :to 1})
(def t_fromTo_3-1 {:from 2 :to 0})

(def t_fromTo001 [t_fromTo_1-2 t_fromTo_3-1])


;; ------------------------------------------
;;  makeBottleLists
;;  defn makePouringLists [fromId toId]
;; ------------------------------------------
(facts "makeBottleLists 01"
  (fact "01"
    (makeBottleLists [10 20] [1 2]) => [{:id 0 :content 1 :capa 10}
                                        {:id 1 :content 2 :capa 20}]
  )
)

;; ------------------------------------------
;;  makePouringLists
;; ------------------------------------------
(facts "makePouringLists 01"
  (fact "01"
    (makePouringLists [1 2] [3 4]) => [{:from 1 :to 3}
                                       {:from 2 :to 4}]
    )
)

;; ------------------------------------------
;;  getTargetBottle
;;  defn getTargetBottle [id bottleLists]
;; ------------------------------------------
(facts "getTargetBottle 01"
  (fact "01"
    (getTargetBottle 3 t_bottles1-4) => t_bottle004
  )
)

;; ------------------------------------------
;;  pouringOnetime
;;  defn pouringOnetime [fromBottle toBottle]
;; ------------------------------------------
(facts "pouringOnetime 01"
  (fact "01"
    (pouringOnetime t_bottle001 t_bottle002) => 2
  )
  (fact "02"
    (pouringOnetime t_bottle001 t_bottle003) => 5
  )
  (fact "03"
    (pouringOnetime t_bottle001 t_bottle004) => 5
  )
  (fact "04"
    (pouringOnetime t_bottle005 t_bottle001) => 0
  )
)

;; ---------------------------------------------------------------
;;  updateBottles
;;  (defn updateBottles [fromId toId bottles pouringAmount]
;; ---------------------------------------------------------------
(facts "updateBottles 01"
  (fact "01"
    (updateBottles 0 1 [t_bottle001 t_bottle002] 2)
                    => [t_bottle001_1-2 t_bottle002_1-2]
  )
  (fact "02"
    (updateBottles 0 2 [t_bottle001 t_bottle003] 5)
                    => [t_bottle001_1-3 t_bottle003_1-3]
  )
  (fact "03"
    (updateBottles 0 3 [t_bottle001 t_bottle004] 5)
    => [t_bottle001_1-4 t_bottle004_1-4]
  )
  (fact "04"
    (updateBottles 4 0 [t_bottle005 t_bottle001] 0)
    => [t_bottle005 t_bottle001]
  )
)

;; ------------------------------------------
;;  pouring
;;  defn pouring [pouringLists bottles]
;; ------------------------------------------
;(def t_bottle001 {:id 0 :content 5  :capa 10})
;(def t_bottle002 {:id 1 :content 18 :capa 20})
;(def t_bottle003 {:id 2 :content 3  :capa 30})
;(def t_bottle004 {:id 3 :content 35 :capa 40})
(facts "pouring 01"
  (fact "01"
    ;(def t_fromTo_1-2 {:from 0 :to 1})
    ;(def t_fromTo_3-1 {:from 2 :to 0})
    (pouring t_fromTo001 t_bottles1_2_3_4)
      => [{:id 0 :content 6 :capa 10} t_bottle002_1-2 {:id 2 :content 0 :capa 30} t_bottle004]
    )
)




