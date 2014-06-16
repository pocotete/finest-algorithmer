(ns finest_algorithmer.chap05.FriendsScore)

;; =======================================================================================
;; # 問題の概要
;;
;; ソーシャルネットワーク上の友達数をカウントする。
;; 友達の友達まで友達とすることにする。
;; i番目の人の友達をfriends[i]とし、例えばfriends[1]="NYN"なら、1番目と2番目は友達になる。
;; 例えばi番目とj番目が友達、i番目とk番目が友達なら、j番目とk番目は友達の友達なので友達になる。
;; 最も友達の多い人の友達人数をかえす関数:highestScore を作成せよ。
;; =======================================================================================

; 直接の友達を求める(一人単位)
; args    -> Y or N の文字列
; returns -> [index [友達のindex]]
(defn getOnesFriends [onesFriends]
  (for [indexedFriends (map-indexed (fn [idx itm] [idx itm]) onesFriends)
        :when (= \Y (nth indexedFriends 1))]
    (nth indexedFriends 0)
  )
)

; 直接の友達を求める(全員)
; returns -> [[index [友達のindex]]]
(defn getFriends [friends]
  (map-indexed (fn [idx itm] [idx itm])
    (for [onesFriends friends] (getOnesFriends onesFriends))
  )
)

; 友達の友達を求める
; index化した友達リストから友達の友達のindexを友達Setに追加して返す
(defn addFriendsFriends [myFriendsIdxs friendsList]
  (loop [ret (set myFriendsIdxs) otherFriendsIdxs myFriendsIdxs]
    (if (empty? otherFriendsIdxs) ret
      (let [otherFriendIdx       (first otherFriendsIdxs)
            otherFriendsFriends  (nth (nth friendsList otherFriendIdx) 1)]
        (recur (apply conj ret otherFriendsFriends)
          (rest otherFriendsIdxs)
        )
      )
    )
  )
)

; 自分の友達リストに、友達の友達を追加して、全ての友達を取得し返す
(defn getAllFriends [friends]
  (let [friendsList (getFriends friends)]
    (for [onesFriends friendsList
          :let [myIdx                 (nth onesFriends 0)
                myFriendsIdxs         (filter #(not= myIdx %) (nth onesFriends 1))
                myFriendsFriendsIdxs  (addFriendsFriends myFriendsIdxs friendsList)]
          ]
      [myIdx (filter #(not= myIdx %) myFriendsFriendsIdxs)]
    )
  )
)

; 問題で定義された関数
(defn highestScore [friends]
  (reduce max (map count (map #(nth % 1) (getAllFriends friends))))
)