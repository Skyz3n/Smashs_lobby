package skyzen.rank;

import org.bukkit.entity.Player;
import skyzen.lobby.Lobby;
import skyzen.playercache.PlayerData;

import java.sql.*;

public class SqlConnection
{

    private Connection connection;
    private String urlbase, host, database, user, pass;
    private Lobby pl;

    public SqlConnection(Lobby pl, String urlbase, String host, String database, String user, String pass)
    {
        this.urlbase = urlbase;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pl = pl;
        this.pass = pass;
    }

    public void connection()
    {
        if (!isConnected())
        {
            try
            {
                connection = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
                System.out.println("connected ok");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void disconnect()
    {
        if (isConnected())
        {
            try
            {
                connection.close();
                System.out.println("connected off");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected()
    {
        return connection != null;
    }

    public void createAccount(Player player)
    {
        if (!hasAccount(player))
        {
            try
            {
                PreparedStatement q = connection.prepareStatement("INSERT INTO joueurs(uuid,grade,coins) VALUES (?,?,?)");
                q.setString(1, player.getUniqueId().toString());
                q.setInt(2, Rank.JOUEUR.getPower());
                q.setInt(3, 100);
                q.execute();
                q.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean hasAccount(Player player)
    {
        try
        {
            PreparedStatement q = connection.prepareStatement("SELECT uuid FROM joueurs WHERE uuid = ?");
            q.setString(1, player.getUniqueId().toString());
            ResultSet resultat = q.executeQuery();
            boolean hasAccount = resultat.next();
            q.close();
            return hasAccount;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void setRank(Player player, Rank rank)
    {
        if (pl.dataPlayers.containsKey(player))
        {
            PlayerData dataP = pl.dataPlayers.get(player);
            dataP.setRank(rank);
            pl.dataPlayers.remove(player);
            pl.dataPlayers.put(player, dataP);
        }
    }

    public Rank getRank(Player player)
    {
        if (pl.dataPlayers.containsKey(player))
        {
            PlayerData dataP = pl.dataPlayers.get(player);
            return dataP.getRank();
        }
        return Rank.JOUEUR;
    }

    public int getBalance(Player p)
    {
        if (pl.dataPlayers.containsKey(p))
        {
            PlayerData dataP = pl.dataPlayers.get(p);
            return dataP.getCoins();
        }

        return 0;
    }

    public void addMoney(Player p, int montant)
    {
        if (pl.dataPlayers.containsKey(p))
        {
            PlayerData dataP = pl.dataPlayers.get(p);
            int coins = dataP.getCoins() + montant;
            dataP.setCoins(coins);
            pl.dataPlayers.remove(p);
            pl.dataPlayers.put(p, dataP);
        }
    }

    public void removeMoney(Player p, int montant)
    {
        if (pl.dataPlayers.containsKey(p))
        {
            PlayerData dataP = pl.dataPlayers.get(p);
            int coins = dataP.getCoins() - montant;

            if (coins <= 0)
                return;

            dataP.setCoins(coins);
            pl.dataPlayers.remove(p);
            pl.dataPlayers.put(p, dataP);
        }

    }

    public PlayerData getPlayerData(Player player)
    {
        if (!pl.dataPlayers.containsKey(player))
        {
            try
            {
                PreparedStatement rs = connection.prepareStatement("SELECT coins, grade FROM joueurs WHERE uuid = ?");
                rs.setString(1, player.getUniqueId().toString());
                ResultSet resultats = rs.executeQuery();

                int coins = 0;
                Rank rank = Rank.JOUEUR;

                while (resultats.next())
                {
                    coins = resultats.getInt("coins");
                    rank = Rank.powerToRank(resultats.getInt("grade"));
                }

                PlayerData dataP = new PlayerData();
                dataP.setCoins(coins);
                dataP.setRank(rank);
                return dataP;

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return new PlayerData();
    }

    public void updatePlayerData(Player player)
    {
        if (pl.dataPlayers.containsKey(player))
        {
            PlayerData dataP = pl.dataPlayers.get(player);

            int coins = dataP.getCoins();
            Rank rank = dataP.getRank();
            int power = rank.getPower();

            try
            {
                PreparedStatement rs = connection.prepareStatement("UPDATE joueurs SET grade = ?, coins = ? WHERE uuid = ?");
                rs.setInt(1, power);
                rs.setInt(2, coins);
                rs.setString(3, player.getUniqueId().toString());
                rs.executeUpdate();
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}