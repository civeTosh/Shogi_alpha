package com.github.cive.shogi.Pieces;

import com.github.cive.shogi.Players.Player;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Gin extends Piece{
    public Gin(Point p) {
        super(p);
    }

    @Override
    public String getName() {
        return "銀";
    }
    @Override
    public String getName(Boolean in_English) {
        if (in_English) {
            return "GI";
        } else {
            return getName();
        }
    }

    @Override
    public Set<Point> getRuleOfPiece(int player_type) {
        Set<Point> set = new HashSet<>();
        if(player_type == Player.AHEAD) {
            set.add(new Point(-1, -1));
            set.add(new Point(0, -1));
            set.add(new Point(1, -1));
            set.add(new Point(-1, 1));
            set.add(new Point(1, 1));
        } else {
            set.add(new Point(-1, 1));
            set.add(new Point(0, 1));
            set.add(new Point(1, 1));
            set.add(new Point(-1, -1));
            set.add(new Point(1, -1));
        }

        return set;
    }
    @Override
    public Integer getTypeOfPiece() {
        return Piece.GIN;
    }
}
