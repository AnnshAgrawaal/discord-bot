import io.github.cdimascio.dotenv.Dotenv;
import listeners.EventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class DiscordBot {

    private final Dotenv config;
    private final ShardManager shardManager;
    public DiscordBot() {
        config = Dotenv.configure().load();
        String TOKEN = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.playing("with your mom!"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_PRESENCES);
//        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
//        builder.setChunkingFilter(ChunkingFilter.ALL);
//        builder.enableCache(CacheFlag.ONLINE_STATUS);
        shardManager = builder.build();

//        Event Listeners
        shardManager.addEventListener(new EventListener());

    }

    public Dotenv getConfig() {
        return config;
    }

    public ShardManager getShardManager(){
        return shardManager;
    }
    public static void main(String[] args) {
       DiscordBot bot = new DiscordBot();
    }
}
