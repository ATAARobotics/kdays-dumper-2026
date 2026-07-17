package frc.robot.subsystems;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShootSystem extends SubsystemBase {
    private SparkMax shooterLeft = new SparkMax(Constants.ShooterConstants.ShooterLeftId, MotorType.kBrushless);
    private SparkMax shooterRight =  new SparkMax(Constants.ShooterConstants.ShooterRightId, MotorType.kBrushless);

    private SparkMax feederLeft = new SparkMax(Constants.ShooterConstants.FeederLeftId, MotorType.kBrushless);
    private SparkMax feederRight =  new SparkMax(Constants.ShooterConstants.FeederRightId, MotorType.kBrushless);
    
    public ShootSystem() {
        SparkMaxConfig shooterLeftConfig = new SparkMaxConfig();
        shooterLeftConfig.follow(Constants.ShooterConstants.ShooterRightId, true);
        shooterLeftConfig.idleMode(IdleMode.kCoast);
        shooterLeft.configure(shooterLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig shooterRightConfig = new SparkMaxConfig();
        shooterRightConfig.idleMode(IdleMode.kCoast);
        shooterRight.configure(shooterRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig feederLeftConfig = new SparkMaxConfig();
        feederLeftConfig.follow(Constants.ShooterConstants.FeederRightId, true);
        feederLeftConfig.idleMode(IdleMode.kCoast);
        feederLeft.configure(feederLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig feederRightConfig = new SparkMaxConfig();
        feederRightConfig.idleMode(IdleMode.kCoast);
        feederRight.configure(feederRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public Command runShooter(double power) {
        return run(() -> {
            shooterRight.set(power);
            feederRight.set(power);
        });
    }
}
