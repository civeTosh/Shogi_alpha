package com.github.cive.shogi.Pieces;

import com.github.cive.shogi.Players.AheadPlayer;
import com.github.cive.shogi.GameBoard;
import com.github.cive.shogi.Players.Player;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Piece implements Constant, Cloneable{
    private Point p;
    public Piece(Point p) {
        this.p = p;
    }
    public String getName() {
        return "　";
    }
    public String getName(Boolean in_English) {
        if (in_English) {
            return "  ";
        } else {
            return getName();
        }
    }
    public void setPoint(Point p) {
        this.p = p;
    }
    public Point getPoint() {
        return p;
    }
    @Override
    public Piece clone() throws CloneNotSupportedException {
        Piece c = (Piece)super.clone();
        c.p = new Point(p);
        return c;
    }
    public abstract Set<Point> getRuleOfPiece(int player_type);
    public Set<Point> getCapablePutPoint(Player attacker, Player defender) {
        int player_type = attacker instanceof AheadPlayer ? Player.AHEAD : Player.BEHIND;
        Set<Point> set = new HashSet<>();
        for (Point rule_of_point : this.getRuleOfPiece(player_type)) {
            Point target = new Point(this.getPoint().x + rule_of_point.x, this.getPoint().y + rule_of_point.y);
            if (attacker.getPieceTypeOnBoardAt(target) == 0 && GameBoard.isInGrid(target)) set.add(target);
        }
        return set;
    }
    public abstract Integer getTypeOfPiece();
    public Boolean canPromote(Point dst, boolean isAheadsTurn) {
        //this : src
        boolean aPromote = (0 <= dst.y && dst.y <= 2) || (0 <= this.getPoint().y && this.getPoint().y <= 2);
        boolean bPromote = (6 <= dst.y && dst.y <= 8) || (6 <= this.getPoint().y && this.getPoint().y <= 8);
        int type = this.getTypeOfPiece();
        boolean canPromoteForType = (type >= Piece.FU && type <= Piece.GIN) || type == Piece.KAKU || type == Piece.HISHA;
        if(isAheadsTurn && aPromote && canPromoteForType) {
            return true;
        } else if (!isAheadsTurn && bPromote && canPromoteForType) {
            return true;
        }
        return false;
    }
    public final Piece getPromotePiece() {
        PieceFactory factory = new PieceFactory();
        return factory.createPromoted(getTypeOfPiece(), getPoint());
    }
    public final Piece getDemotePiece() {
        PieceFactory factory = new PieceFactory();
        return factory.createDemoted(getTypeOfPiece(), getPoint());
    }
    public Boolean canMoveLater(Player attacker) {
        int player_type = attacker instanceof AheadPlayer ? Player.AHEAD : Player.BEHIND;
        for (Point rule_of_point : this.getRuleOfPiece(player_type)) {
            Point target = new Point(this.getPoint().x + rule_of_point.x, this.getPoint().y + rule_of_point.y);
            if (GameBoard.isInGrid(target)) return true;
        }
        return false;
    }
    public Boolean isOnBoard() {
        return 0 <= p.x && p.x <= 8;
    }
    @Override
    public String toString() {
        return getName();
    }
    public Boolean isEmpty() {
        return this.getTypeOfPiece() == Piece.NONE;
    }
}
