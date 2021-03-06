package com.github.cive.shogi;

import com.github.cive.shogi.Pieces.Piece;
/**
 * Created by yotuba on 16/05/20.
 * 盤面のデータ構造
 */
public class MovementOfPiece {
    public MovementOfPiece(Piece src, Piece dst) {
        setDst(dst);
        setSrc(src);
    }
    private Piece src;
    private Piece dst;
    public void setDst(Piece dst) {
        try {
            this.dst = dst.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public void setSrc(Piece src) {
        try {
            this.src = src.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public Piece getDst() throws CloneNotSupportedException{
        return dst.clone();
    }
    public Piece getSrc() throws CloneNotSupportedException {
        return src.clone();
    }
    @Override
    public String toString() {
        return "TODO";
    }
}