import React, { FunctionComponent } from 'react';
import "./SweepstakeCard.css";
import Button from "../Button/Button";

interface OwnProps {}

type Props = OwnProps;

const SweepstakeCard: FunctionComponent<Props> = (props) => {
  return (
      <div className="col-md-5 col-lg-4">
          <div className="clean-pricing-item">
              <div className="heading">
                  <h4>Jon's Corner Crazy</h4>
              </div>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <Button label={"Buy Tickets"} />
          </div>
      </div>
  );
};

export default SweepstakeCard;
