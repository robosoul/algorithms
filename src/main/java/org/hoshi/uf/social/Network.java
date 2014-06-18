/**
 * Copyright (C) 2014 Luka Obradovic.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.hoshi.uf.social;

import org.hoshi.uf.UF;
import org.hoshi.uf.WeightedQuickUnionPathCompressionUF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a list of friends on a social network.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class Network {
    public static final Logger log = LoggerFactory.getLogger(Network.class);

    private int n = 0;
    private final UF uf;
    private final Map<Friend, Integer> friends;

    public Network(final int n) {
        this.uf = new WeightedQuickUnionPathCompressionUF(n);
        this.friends = new HashMap<Friend, Integer>();
    }

    public void add(final Friend friend) {
        if (friend == null) {
            throw new IllegalArgumentException("null friend.");
        }

        if (!friends.containsKey(friend)) {
            friends.put(friend, n++);
        }
    }

    public void connect(final Friend f1, final Friend f2) {
        uf.union(friends.get(f1), friends.get(f2));
    }

    public boolean areConnected(final Friend f1, final Friend f2) {
        return uf.connected(friends.get(f1), friends.get(f2));
    }
}