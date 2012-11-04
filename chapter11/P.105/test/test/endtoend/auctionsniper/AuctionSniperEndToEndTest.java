package test.endtoend.auctionsniper;

import org.junit.After;
import org.junit.Test;


public class AuctionSniperEndToEndTest {
    private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
    private final ApplicationRunner application = new ApplicationRunner();

    @Test
    public void sniperJoinsAuctionUntiAuctionCloses() throws Exception{
        auction.startSellingItem();						// ステップ１
        application.startBiddingIn(auction);			// ステップ２
        auction.hasReceivedJoinRequestFromSniper();		// ステップ３
        auction.announceClossed();						// ステップ４
        application.showsSniperHasLostAuction();		// ステップ５
    }

    // 追加のクリーンアップ
    @After
    public void stopAuction(){
        auction.stop();
    }

    @After
    public void stopApplication(){
        application.stop();
    }
}
