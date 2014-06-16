(ns finest_algorithmer.chap04.KiwiJuiceEasy)

;; =================================================================
;; # 問題の概要
;;
;; 容量=capacities[i]のボトルiから容量=capacities[j]のボトルjへ水を注ぐ。
;; k回目の操作で、fromId[k]のボトルからtoId[k]のボトルへ水を注ぐ。
;; k回目の操作で、fromが空かtoが満タンになったら、k+1回目の操作に移行する。
;; 全て操作が終わった後のボトル状態をかえす関数:thePouring を作成せよ。
;; =================================================================

; ボトル単位のデータ構造を作成
(defn makeBottleLists [capacities bottles]
  (for [k (range (count capacities))]
    {:id k
     :capa (nth capacities k)
     :content (nth bottles k)}
  )
)

; 操作単位のデータ構造(注ぐFrom-Toのリスト)を作成
(defn makePouringLists [fromId toId]
  (for [k (range (count fromId))]
    {:from (nth fromId k)
     :to (nth toId k)}
  )
)

; 注ぐ一回の処理
(defn pouringOnetime [fromBottle toBottle]
  (let [fromContent  (:content fromBottle)
        toVacancy    (- (:capa toBottle) (:content toBottle))]
    (cond
      ; 注ぐ量が0の場合は0
      (= 0 fromContent) 0
      ; From残りがあり、Toが満杯になるパターン
      ; 例) 5/10 -> 3/5
      (> fromContent toVacancy) toVacancy
      ; Fromが空、Toが満杯になるパターン
      ; 例) 5/10 -> 15/20
      (= fromContent toVacancy) fromContent
      ; Fromが全部注いで空、Toはまだ空きがあるパターン
      ; 例) 5/10 -> 1/10
      (< fromContent toVacancy) fromContent
    )
  )
)

; 注入後のボトル状態のリストを作成
(defn updateBottles [fromId toId bottles pouringAmount]
  ; 注ぐ量が0の場合は、変化しないのでそのまま返す
  (if (= 0 pouringAmount) bottles
    (for [bottle bottles]
      (assoc
        (cond
          ; from
          (= (:id bottle) fromId)
          {:content (- (:content bottle) pouringAmount)}
          ; to
          (= (:id bottle) toId)
          {:content (+ (:content bottle) pouringAmount)}
          ; その他
          :else
          {:content (:content bottle)}
        )
        :capa (:capa bottle)
        :id   (:id   bottle)
      )
    )
  )
)

; 該当idのボトルを返す
(defn getTargetBottle [id bottleLists]
  (first (filter #(= id (:id %)) bottleLists))
)

; 注ぐFrom-Toリストをループして実行
(defn pouring [pouringLists bottleLists]
  (loop [pouringLists pouringLists updatedBottleLists bottleLists]
    (if (empty? pouringLists) updatedBottleLists
      (let [fromBottle (getTargetBottle (:from (first pouringLists)) updatedBottleLists)
            toBottle   (getTargetBottle (:to   (first pouringLists)) updatedBottleLists) ]
        (let [result (pouringOnetime fromBottle toBottle)
              afterBottleLists (updateBottles (:id fromBottle) (:id toBottle) updatedBottleLists result)]
          (recur (rest pouringLists) afterBottleLists)
        )
      )
    )
  )
)

; 問題で定義された関数
(defn thePouring [capacities bottles fromIds toIds]
  (let [bottleLists   (makeBottleLists capacities bottles)
        pouringLists  (makePouringLists fromIds toIds)
        retBottles    (pouring pouringLists bottleLists)
        bRange        (range (count bottles))]
    (for [b bRange] (:content (getTargetBottle b retBottles)))
  )
)