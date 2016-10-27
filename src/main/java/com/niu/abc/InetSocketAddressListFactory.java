package com.niu.abc;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.HostAndPort;

public class InetSocketAddressListFactory {

	private Collection<InetSocketAddress> addresses;
	private Set<HostAndPort> redisAddress;

	public InetSocketAddressListFactory(String addressesString) {
		String[] addressStrings = addressesString.split(",");
		String host = "";
		int port = 0;
		String[] addressString = null;
		InetSocketAddress address = null;
		HostAndPort redisAddres = null;
		addresses = new ArrayList<InetSocketAddress>();
		redisAddress = new HashSet<HostAndPort>();
		for (int i = 0; i < addressStrings.length; i++) {
			addressString = addressStrings[i].split(":");
			host = addressString[0].trim();
			port = Integer.parseInt(addressString[1].trim());
			address = new InetSocketAddress(host, port);
			redisAddres = new HostAndPort(host, port);
			addresses.add(address);
			redisAddress.add(redisAddres);
		}
	}

	public List<InetSocketAddress> createAddresses() {
		List<InetSocketAddress> adds = new ArrayList<InetSocketAddress>();
		adds.addAll(addresses);
		return adds;
	}

	public Set<HostAndPort> createAddresses2() {
		Set<HostAndPort> adds = new HashSet<HostAndPort>();
		adds.addAll(redisAddress);
		return adds;
	}
}
