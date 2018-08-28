package com.chesschecker.input;

import com.chesschecker.util.PieceAbbreviation;
import com.google.common.base.Predicates;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@SuppressWarnings({"serial", "CloneableClassWithoutClone", "CloneableClassInSecureContext"})
class KingProtectionMoveList extends MoveList {

    /**
     * This constructor first generates a list of imposter positions, then creates
     * a move list for them by calling super
     *
     * @param positions: list of positions
     */
    KingProtectionMoveList(final Collection<String> positions) {
        super(positions.stream()
                .filter(Predicates.containsPattern('^' + PieceAbbreviation.KING.getAbbreviation()))
                .map(KingProtectionMoveList::generateImpostors)
                .map(Collection::stream).flatMap(x -> x).collect(Collectors.toList()));
    }

    /**
     * @param kingPosition the position for the king
     * @return a set of positions such that the King has been replaced by each type
     * of piece inclusive (though the king is not necessary).
     */
    private static Collection<String> generateImpostors(final String kingPosition) {
        final String kingLocation = kingPosition.substring(1);//crop off the King part
        final Collection<String> impostors = new HashSet<>(6);
        for (final PieceAbbreviation pieceAbbreviation : PieceAbbreviation.values()) {
            impostors.add(pieceAbbreviation.getAbbreviation() + kingLocation);
        }
        return impostors;
    }
}
