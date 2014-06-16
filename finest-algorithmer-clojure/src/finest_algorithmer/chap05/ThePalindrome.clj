(ns finest_algorithmer.chap05.ThePalindrome)

;; ================================================================================
;; # 問題の概要
;;
;; 回文(山本山)を作成する。
;; 与えられた文字列sに最小の文字列を追加し回文を作成し、回文の文字数を返す関数:find を作成せよ。
;; 例)
;;  s:"abab"  -> 5 (add "a")
;;  s:"abcba" -> 5 (add nothing)
;;  s:"apple" -> 9 (add "lppa")
;; ================================================================================

;; 回文かどうかを判定する
(defn examine [s]
  (= (seq s) (reverse s))
)

;; 与えられた文字列に対し、回文を作成する
(defn makeYamamotoyama [s]
  (loop [ret s
         addStr  (str (first s))
         restStr (apply str (rest s))]
    (if (examine ret) ret
      (if (empty? restStr) ret
        (recur (apply str s (reverse addStr))
               (str addStr (first restStr))
               (apply str (rest restStr))
        )
      )
    )
  )
)

; 問題で定義された関数
(defn find [s]
  (count (makeYamamotoyama s))
)

